package work.adapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by JLyc on 1. 4. 2015.
 */
public class GetNodeNames {

    private static String msg = "<con:snmp-request-message xmlns:con=\"http://www.cgi.com/eai/adapter/custom/telecom/config\">\n" +
            "  <con:snmp-version>1</con:snmp-version>\n" +
            "  <con:ip-address>127.0.0.1</con:ip-address>\n" +
            "  <con:port>161</con:port>\n" +
            "  <con:community>Public</con:community>\n" +
            "  <con:request-type>GET</con:request-type>\n" +
            "  <con:oid-value>.1.3.6.1.2.1.1.1.0</con:oid-value>\n" +
            "  <con:retries-count>2</con:retries-count>\n" +
            "  <con:timeout>10</con:timeout>\n" +
            "</con:snmp-request-message>";

    public static void main(String[] args) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(msg));
            Document doc = dBuilder.parse(is);
            Element rootElement = doc.getDocumentElement();
            if(rootElement.hasChildNodes()){
                NodeList list = rootElement.getChildNodes();

                for(int i=0;i<list.getLength();i++) {
                    if (list.item(i).getNodeType()==(Node.ELEMENT_NODE)) {
                        System.out.println(list.item(i).getNodeName().replaceFirst(".*:",""));
                        System.out.println(list.item(i).getTextContent());

                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
