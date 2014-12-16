package neural_center.memory.initialize_memory.helpers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

/**
 * Created by sochaa on 16. 12. 2014.
 */
public class DataStoresScheme {
    protected Object rawData;

    protected ArrayList<ArrayList<String>> dataIn3DCube = new ArrayList<>();
    protected Document dataInXMLDoc;

    public DataStoresScheme(Object rawData) {
        this.rawData = rawData;
    }

    public ArrayList<ArrayList<String>> getDataIn3DCube() {
        if (dataIn3DCube == null) {
            model3DCube();
            return dataIn3DCube;
        } else {
            return dataIn3DCube;
        }
    }

    public Document getDataInXMLDoc() throws NoSuchObjectException {
        if (rawData instanceof Document)
            return dataInXMLDoc = (Document) rawData;
        else throw new NoSuchObjectException("Can't return required format");
    }

    private ArrayList<ArrayList<String>> model3DCube() {
        recursive(doc.getChildNodes());


        return null;
    }

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

    public void write(final Element element)
    {
        if (!(element.getNodeType() == Node.TEXT_NODE)) {
            String identifier =
            int existPosition = nodeExist(element);

            if()
            dataIn3DCube.add(new ArrayList<String>());
            dataIn3DCube.get(0).add(String.valueOf(element.getParentNode().getAttributes().item(0)));
        }
    }

    public int nodeExist(Element element){
        for(int index = 0; index<dataIn3DCube.size();index++){
            if(dataIn3DCube.get(index).get(0).equals(element.get)){
                return index;}
        }
        return -1;
    }

    public static String write1(final Element element) {
        element.normalize();
//        Element e = (Element) element.getElementsByTagName();
        String out = element.getNodeName();

        for (int index = 0; index < element.getAttributes().getLength(); index++) {
            out += element.getAttributes().item(index) + " ";
        }

        return out;
    }


}
