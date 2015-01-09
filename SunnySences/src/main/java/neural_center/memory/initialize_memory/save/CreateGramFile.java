package neural_center.memory.initialize_memory.save;

import neural_center.memory.SunnyMemory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import uniqe_skills.smart_xpath.SmartXPath;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Created by sochaa on 2. 1. 2015.
 */
public class CreateGramFile
{
    private static Path pathInBrain = FileSystems.getDefault().getPath(String.valueOf(SunnyMemory.BRAIN), "sunny_windows.gram");
    private static StringBuilder gramFileContent = new StringBuilder("#JSGF V1.0;\n\n/**\n * Sunny basic knowleadge\n */\n\ngrammar Sunny;\n\n");
    private static Document document;
    private static String[][] sets = {
            {"",""},
            {"("," ) "},
            {"["," } "},
    };

    private static void fillPartsWithBrackets(NodeList nodeList, int modifier) {
        gramFileContent.append(sets[modifier][0]);
        for (int index = 0; index < nodeList.getLength(); index++) {
            gramFileContent.append(" "+nodeList.item(index).getTextContent());
            if(index!=nodeList.getLength()-1) {
                gramFileContent.append(" | ");
            }
        }
        gramFileContent.append(sets[modifier][1]);
    }

    private static void createGramFile()
    {
        try {
            //get number
            SmartXPath smartXPath = new SmartXPath().from(document);
            int rows = smartXPath.forExpresion("/root/row").asNode().getChildNodes().getLength();

            int modifier;
            for(int index=0;index<=rows;index++) {
                modifier = Integer.parseInt(smartXPath.forExpresion("/root/row[@id='" + index + "']").asNode().getAttributes().item(0).getNodeValue());
                fillPartsWithBrackets(smartXPath.forExpresion("/root/row[@id='" + index + "']/word").asNodeList(), modifier);
            }
            gramFileContent.append(";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveFile()
    {
        if(Files.notExists(pathInBrain, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createFile(pathInBrain);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(pathInBrain, Charset.forName("UTF-8"), WRITE, TRUNCATE_EXISTING)){
            writer.write(String.valueOf(gramFileContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createGramFile(Document doc) {
        document = doc;
        createGramFile();
        saveFile();
    }
}
