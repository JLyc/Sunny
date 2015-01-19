package test_package;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public static String write(final Element element) {
        element.normalize();
//        Element e = (Element) element.getElementsByTagName();
        String out = element.getNodeName();

        for (int index = 0; index < element.getAttributes().getLength(); index++) {
            out += element.getAttributes().item(index) + " ";
        }
        if (!(element.getNodeType() == Node.TEXT_NODE)) {
            out += " " + element.getFirstChild().getNodeValue();
        }
        return out;
    }

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        try (InputStream is = Node.class.getResourceAsStream("/commands_windows.xml")) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Document doc = dBuilder.parse(String.valueOf(reader));


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
    }
}
