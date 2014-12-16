package neural_center.memory.initialize_memory.load;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

/**
 * Created by sochaa on 16. 12. 2014.
 */
public class LoadXmlFile extends LoadAnyFile {

    public LoadXmlFile(String path) {
        super(path);
    }

    public LoadXmlFile(URI path) {
        super(path);
    }

    @Override
    protected Object proceedFile() throws IOException {
        try {
            InputStream is = loadInputStreamFile();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }

    }
}
