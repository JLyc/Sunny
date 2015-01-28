package neural_center.listening.commandHandler;

import neural_center.initialization.BasicKnowledge;
import neural_center.listening.commandHandler.run_application.CommandProcessor;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uniqe_skills.smart_xpath.SmartXPath;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by JLyc on 22. 1. 2015.
 */
public class ReceivedCommand implements Runnable{
    private static  String errMessage = "no error";

    private static NodeList grammarList;
    private static NodeList recognizedWordList;
    private static Set<String> recognizedCommands = new TreeSet<>();

    public static boolean updateKnowingWords() throws Exception {
        SmartXPath xPath = new SmartXPath();
        grammarList = xPath.from(BasicKnowledge.getInstance().get("grammarForListening")).forExpression("/root/row[@condition>'0']").asNodeList();
        recognizedWordList = xPath.from(BasicKnowledge.getInstance().get("recognizedWords")).forExpression("/root/modifiers/modifier").asNodeList();
        NodeList nodeOfCommands = xPath.from(BasicKnowledge.getInstance().get("commandsSource")).forExpression("/root/commands/command").asNodeList();
        for (int index = 0; index < nodeOfCommands.getLength(); index++) {
            recognizedCommands.add(nodeOfCommands.item(index).getAttributes().getNamedItem("lyc:id").getTextContent());
        }
        return true;
    }

    private String command = null;

    public ReceivedCommand(String command) {
        this.command = command;
    }

    private boolean isCommand() {
        if (command.contains("sunny")) {
            int index = 0;
            boolean isCommand;
            do {
                isCommand = false;
                NodeList test = grammarList.item(index).getChildNodes();
                for (int j = 0; j < test.getLength(); j++) {
                    if (test.item(j).getNodeType() == Node.ELEMENT_NODE && command.contains(test.item(j).getTextContent())) {
                        isCommand = true;
                        break;
                    }
                }
                if (++index == grammarList.getLength()){
                    errMessage = "Command not contain word "+command+" from: " + grammarList.item(index-1).getAttributes().getNamedItem("lyc:id").getTextContent();
                    break;
                }
            } while (isCommand);
            return isCommand;
        } else {
            errMessage = "No sunny word";
            return false;
        }
    }

    private String getCommandName() {
        for (String knowCommand : recognizedCommands) {
            if (command.contains(knowCommand)) {
                return knowCommand;
            }
        }
        errMessage = command + "\\ not contain words \\"+ recognizedCommands;
        return null;
    }

    private String getCommandModifier() {
        int index = 0;
        while (true) {
            NodeList test = recognizedWordList.item(index).getChildNodes();
            for (int j = 0; j < test.getLength(); j++) {
                if (test.item(j).getNodeType() == Node.ELEMENT_NODE && command.contains(test.item(j).getTextContent())) {
                    return recognizedWordList.item(index).getAttributes().getNamedItem("lyc:id").getTextContent();
                }
            }
            index++;
            errMessage = "error in modifier";
        }
    }

    @Override
    public void run() {
        proceedCommand();
    }

    private void proceedCommand() {
        if (isCommand()) {
            String commandName = getCommandName();
            String commandModifier = getCommandModifier();
            if (commandName != null && commandModifier != null) {
                List<String> list = new SmartXPath().from(BasicKnowledge.getInstance().get("commandsSource")).forExpression("/root/commands/command[@id='" + commandName + "' and @modifier='" + commandModifier + "']").asList();
                CommandProcessor.executeCommand(list.get(0), list.get(1), list.get(2));
            } else {
                //TODO not implemented
            }
        } else {
            System.err.println("No valid command! "+errMessage);
        }
    }
}