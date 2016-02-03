/*
 * Copyright (c) 2015. All rights reserved for author and owner of email domain JLyc.Development@gmail.com.
 */

package test_package;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.*;
import javax.xml.validation.*;
import java.io.*;

/**
 * Created 16. 12. 2015.
 *
 * @author JLyc
 */
public class Doc {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        doc.appendChild(doc.createElementNS(null, "root"));
        System.out.println(doc.toString());
        SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema();
        doc.getDocumentElement().normalize();
    }

}
