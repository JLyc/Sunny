package neural_center.initialization;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.InputStream;

/**
 * Created by sochaa on 11. 12. 2014.
 */
public class Nodemode {

    public static void recursive(final NodeList nList) {
        for (int index = 0; index < nList.getLength(); index++) {
            Node node = nList.item(index);
            if (node.getNodeType() == Node.ELEMENT_NODE){
            Element eElement = (Element) node;
            System.out.println(write(eElement));
            if (eElement.hasChildNodes()) {
                recursive(eElement.getChildNodes());
            }}
        }
    }

    public static String write(final Element element)
    {
        element.normalize();
//        Element e = (Element) element.getElementsByTagName();
        String out = element.getNodeName();

        for(int index=0;index<element.getAttributes().getLength();index++)
        {
            out += element.getAttributes().item(index) + " ";
        }
        out += " "+ element.getFirstChild().getNodeValue() ;
        return out;
    }

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        try (InputStream is = Node.class.getResourceAsStream("/commands_windows.xml")) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            if (doc.hasChildNodes()) {
                recursive(doc.getChildNodes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch exception");

        }

//
//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//            System.out.println(doc.hasChildNodes());
//
//            NodeList nList = doc.getChildNodes();
//
//            System.out.println("----------------------------");
//
//
//            System.out.println(nList.getLength());
//            for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                Node nNode = nList.item(temp);
//
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element eElement = (Element) nNode;
//
//                    System.out.println(eElement.getNodeName());
//
////					System.out.println("Element " + eElement.getE);
////					System.out.println("Staff id : " + eElement.getAttribute("id"));
////					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
////					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
////					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
////					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//
//                }
//            }
//
//            Path BRAIN = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "Brain", "Windows", "output.xml");
//
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            Result output = new StreamResult(BRAIN.toFile());
//            Source input = new DOMSource(doc);
//
//            transformer.transform(input, output);
//
//        } catch (Exception e) {
//            System.out.println("catch exception");
//            e.getStackTrace();
//        }
    }
}
