package uniqe_skills.smart_xpath;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sochaa on 2. 1. 2015.
 */
public class SmartXPath {
    private static XPathFactory xPathFactory = XPathFactory.newInstance();
    private static XPath xPath = xPathFactory.newXPath();

    private Document document;
    private String expression;
    private Object result;

    public SmartXPath from(Document document) {
        this.document = document;
        return this;
    }

    public SmartXPath forExpresion(String expression) {
        this.expression = expression;
        return this;
    }

    private synchronized void result(QName type) {
        synchronized (xPath) {
            try {
                result = xPath.compile(expression).evaluate(document, type);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            } finally {
                reuseInstance();
            }
        }
    }

    public NodeList asNodeList() {
        result(XPathConstants.NODESET);
        return (NodeList) result;
    }

    public List<String> asList() {
        List<String> list = new ArrayList<>();
        result(XPathConstants.NODESET);
        NodeList result = (NodeList) this.result;

        for (int index = 0; index < result.getLength(); index++) {
            list.add(result.item(index).getTextContent());
        }
        return list;
    }

    public String asString() {
        result(XPathConstants.STRING);
        return (String) result;
    }

    public boolean asBoolean() {
        result(XPathConstants.BOOLEAN);
        return (boolean) result;

    }

    public Node asNode() {
        result(XPathConstants.NODE);
        return (Node) result;
    }

    public Number asNumber() {
        result(XPathConstants.NUMBER);
        return (Number) result;
    }

    private static void reuseInstance() {
        xPath.reset();
    }
}
