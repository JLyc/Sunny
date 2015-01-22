import neural_center.initialization.BasicKnowledge;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uniqe_skills.smart_xpath.SmartXPath;

/**
 * Created by JLyc on 22. 1. 2015.
 *
 */
public class SmartXPathTest {

    @Test
    public void testIfIsValidCommandXPath() {
        System.out.println(helpMethod());
    }

    public boolean helpMethod(){
        String constant = "sunny run fire fox";
        NodeList nl = new SmartXPath().from(BasicKnowledge.getInstance().get("grammarForListening")).forExpresion("/root/row[@condition>'0']").asNodeList();
        for (int i = 0; i < nl.getLength(); i++) {
            NodeList test = nl.item(i).getChildNodes();
            for (int j = 0; j < test.getLength(); j++){
                    if(test.item(j).getNodeType()== Node.ELEMENT_NODE && constant.contains(test.item(j).getTextContent())){
                    System.out.println(test.item(j).getTextContent());
                    break;
                    }
            }
        }
        return true;
    }//test.item(j).getNodeType()== Node.TEXT_NODE &&
}
