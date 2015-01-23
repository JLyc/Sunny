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
public class ReceivedCommand {
    private static  String errMessage = "no error";

    private static NodeList wordList1;
    private static NodeList wordList2;
    private static Set<String> wordList4 = new TreeSet<>();

    public static boolean updateKnowingWords() throws Exception {
        wordList1 = new SmartXPath().from(BasicKnowledge.getInstance().get("grammarForListening")).forExpression("/root/row[@condition>'0']").asNodeList();
        wordList2 = new SmartXPath().from(BasicKnowledge.getInstance().get("recognizedWords")).forExpression("/root/modifiers/modifier").asNodeList();
        NodeList wordList3 = new SmartXPath().from(BasicKnowledge.getInstance().get("commandsSource")).forExpression("/root/commands/command").asNodeList();
        for (int index = 0; index < wordList3.getLength(); index++) {
            wordList4.add(wordList3.item(index).getAttributes().getNamedItem("lyc:id").getTextContent());
        }
        return true;
    }

    public ReceivedCommand(String command) {
        if (isCommand(command)) {
            String commandName = getCommandName(command);
            String commandModifier = getCommandModifier(command);
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

    private boolean isCommand(String command) {
        if (command.contains("sunny")) {
            int index = 0;
            boolean isCommand;
            do {
                isCommand = false;
                NodeList test = wordList1.item(index).getChildNodes();
                for (int j = 0; j < test.getLength(); j++) {
                    if (test.item(j).getNodeType() == Node.ELEMENT_NODE && command.contains(test.item(j).getTextContent())) {
                        isCommand = true;
                        break;
                    }
                }
                if (++index == wordList1.getLength()){
                    errMessage = "Command not contain word "+command+" from: " + wordList1.item(index-1).getAttributes().getNamedItem("lyc:id").getTextContent();
                    break;
                }
            } while (isCommand);
            return isCommand;
        } else {
            errMessage = "No sunny word";
            return false;
        }
    }

    private String getCommandName(String command) {
        for (String knowCommand : wordList4) {
            if (command.contains(knowCommand)) {
                return knowCommand;
            }
        }
        errMessage = command + "\\ not contain words \\"+ wordList4;
        return null;
    }

    private String getCommandModifier(String command) {
        int index = 0;
        while (true) {
            NodeList test = wordList2.item(index).getChildNodes();
            for (int j = 0; j < test.getLength(); j++) {
                if (test.item(j).getNodeType() == Node.ELEMENT_NODE && command.contains(test.item(j).getTextContent())) {
                    return wordList2.item(index).getAttributes().getNamedItem("lyc:id").getTextContent();
                }
            }
            index++;
            errMessage = "error in modifier";
        }

    }
}