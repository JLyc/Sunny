/*
 * Copyright (c) 2015. All rights reserved for author and owner of email domain JLyc.Development@gmail.com.
 */

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.nio.file.*;

/**
 * Created 11. 10. 2015.
 *
 * @author JLyc
 */
public class EZTEST {


    public static void main(String[] args) {
        try {
           Path path = FileSystems.getDefault().getPath("D:\\OPDB.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(path.toFile());




            doc.getDocumentElement().normalize();

        SmartXPath xPath = new SmartXPath();

//        NodeList nodeList = xPath.forExpression("/application/NVPairs[@name='Global Variables']").from(doc).asNodeList();
//        NodeList nodeChaildsList = nodeList.item(0).getChildNodes();
//
//            for(int i=0;i<nodeChaildsList.getLength();i++) {
//                if(nodeChaildsList.item(i).getNodeType()==Node.ELEMENT_NODE){
//                Node node = nodeChaildsList.item(i);
//                System.out.println(node.getChildNodes().item(1).getTextContent() + " => " + node.getChildNodes().item(3).getTextContent());
//                }
//            }

            NodeList nodeList = xPath.forExpression("/application/services/bw").from(doc).asNodeList();
            for(int i =0 ;i < nodeList.getLength();i++){
                NodeList nodeList1 = xPath.forExpression("/application/services/bw[@name='"+nodeList.item(i).getAttributes().item(0)+"']/bindings/binding/setting").from(doc).asNodeList()
                for(int j =0 ;j < nodeList1.getLength();j++){
                    if(nodeList1.item(i).getNodeType()==Node.ELEMENT_NODE){

                    }
                }
            }


            System.out.println(nodeList.getLength());


        } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
