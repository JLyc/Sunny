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
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Created by sochaa on 16. 12. 2014.
 */
public class LoadXmlFile implements Callable<Document> {
    private Path path;

    public LoadXmlFile(String path) {
        this.path = FileSystems.getDefault().getPath(path);
    }

    private Document proceedFile() throws IOException {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(path.toString());
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

    @Override
    public Document call() throws Exception {
        return proceedFile();
    }
}
