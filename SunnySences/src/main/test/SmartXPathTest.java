import neural_center.initialization.BasicKnowledge;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uniqe_skills.smart_xpath.SmartXPath;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by JLyc on 22. 1. 2015.
 *
 */
public class SmartXPathTest {

    @Test
    public void testIfIsValidCommandXPath() {
        System.out.println(isCommandTest());
        System.out.println(isExecutableCommandTest());
        System.out.println(fillSetTest());
    }

    public boolean isCommandTest() {
        String constant = "sunny show note pad";
        NodeList nl = new SmartXPath().from(BasicKnowledge.getInstance().get("grammarForListening")).forExpression("/root/row[@condition>'0']").asNodeList();
        int index = 0;
        boolean isCommand;
        do {
            isCommand = false;
            NodeList test = nl.item(index).getChildNodes();
            for (int j = 0; j < test.getLength(); j++) {
                if (test.item(j).getNodeType() == Node.ELEMENT_NODE && constant.contains(test.item(j).getTextContent())) {
                    System.out.println(test.item(j).getTextContent());
                    isCommand = true;
                    break;
                }
            }
            if (++index == nl.getLength()) break;
        } while (isCommand);
        return isCommand;
    }

    private String isExecutableCommandTest() {
        String constant = "sunny show fox fire";
        NodeList nl = new SmartXPath().from(BasicKnowledge.getInstance().get("recognizedWords")).forExpression("/root/modifiers/modifier").asNodeList();
        int index = 0;
        while (true) {
            NodeList test = nl.item(index).getChildNodes();
            for (int j = 0; j < test.getLength(); j++) {
                if (test.item(j).getNodeType() == Node.ELEMENT_NODE && constant.contains(test.item(j).getTextContent())) {
                    return nl.item(index).getAttributes().getNamedItem("lyc:id").getTextContent();
                }
            }
            index++;
        }
    }

    private String fillSetTest() {
        Set<String> out = new TreeSet<>();
        NodeList nl = new SmartXPath().from(BasicKnowledge.getInstance().get("commandsSource")).forExpression("/root/commands/command").asNodeList();

        for(int index = 0; index<nl.getLength();index++) {
            out.add(nl.item(index).getAttributes().getNamedItem("lyc:id").getTextContent());
        }
        return String.valueOf(out.size());
    }

}