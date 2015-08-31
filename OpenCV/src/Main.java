import main_function_wrapper.*;
import org.opencv.core.*;
import org.opencv.imgproc.*;

import java.awt.image.*;

/**
 * Properties
 * -Djava.library.path="C:\Users\sochaa\Downloads\opencv\build\java\x64"
 */

public class Main {
    public static void main( String[] args ) {

        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

//            BufferedImage image = CustomImageLoader.captureScreen();
            BufferedImage image = CustomImageLoader.loadImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert1.jpg");

            Mat source = CustomImageLoader.createMat(image, CvType.CV_8UC3);
            Mat destin = CustomImageLoader.emptyMatFrom(image, CvType.CV_8UC3);

            Imgproc.cvtColor(source, destin, Imgproc.COLOR_RGB2GRAY);

            RenderedImage renderedImage = CustomImageLoader.renderedImage(destin, BufferedImage.TYPE_BYTE_GRAY);

            CustomImageLoader.saveImage(renderedImage,"C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert1.jpg");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}



























/*
<?xml version="1.0" encoding="UTF-8"?>
<pfx4:networkPort xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns="http://www.tibco.com/pe/DeployedVarsType" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ns12="http://www.tibco.com/pe/GenerateErrorActivity/InputSchema" xmlns:ns13="www.tibco.com/plugin/Sleep" xmlns:pfx="http://www.t-mobile.sk/soa/CustomerServiceInterface/xsd" xmlns:pd="http://xmlns.tibco.com/bw/process/2003" xmlns:ns2="http://www.tibco.com/pe/EngineTypes" xmlns:ns1="http://www.logica.com/eurotel-crm" xmlns:pfx5="http://www.tibco.com/ns/no_namespace_schema_location/Resources/SchemaDefinitions/BLOS_ST_v3.xsd" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:tib="http://www.tibco.com/bw/xslt/custom-functions" xmlns:pfx4="http://www.t-mobile.sk/oss/blosmeasure/xsd" xmlns:pfx3="urn:cmg.stdapp.webservices.generalplugin" xmlns:pfx2="http://schemas.tibco.com/bw/plugins/xml/5.0/xmlExceptions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:pfx6="http://www.tibco.com/ns/no_namespace_schema_location/Resources/SchemaDefinitions/BLOS_ST_v4.xsd">
<xsl:if test="$Map-Request-Data-For-BLOS/xmlAPIstart/@Type">
<ns1:status>
<xsl:value-of select="$Map-Request-Data-For-BLOS/xmlAPIstart/@Type"/>
</ns1:status>
</xsl:if>
<xsl:if test="string-length($Parse-XML/LSI_HISTORY/DeviceData/Port)>0">
<ns1:number>
<xsl:value-of select="$Parse-XML/LSI_HISTORY/DeviceData/Port"/>
</ns1:number>
</xsl:if>
<ns1:nodeId>
<xsl:value-of select="$Parse-XML/LSI_HISTORY/DeviceData/DeviceName"/>
</ns1:nodeId>
<ns1:slotNumber>
<xsl:value-of select="$Parse-XML/LSI_HISTORY/DeviceData/Slot"/>
</ns1:slotNumber>
</pfx4:networkPort>*/
