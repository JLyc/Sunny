package neural_center.memory.initialize_memory.save;

import neural_center.initialization.BasicKnowledge;
import neural_center.initialization.EnvironmentOfOS;
import neural_center.memory.Memory;
import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

/**
 * Created by sochaa on 11. 12. 2014.
 */
//TODO simple save
public class SaveXmlFile implements Callable<Boolean>{
    private Document doc;
    private String resourcesKey;
    private Path destinationFile;

    @Override
    public Boolean call() throws Exception {
        return writeData();
    }

    public SaveXmlFile(String resourcesKey) {
        this.doc = BasicKnowledge.getInstance().get(resourcesKey);
        this.resourcesKey = resourcesKey;
        this.destinationFile = Memory.getInstance().getPathInMemory("Persistent").resolve(EnvironmentOfOS.getInstance().getProperties(resourcesKey));
    }

    protected void saveToBrain() {
    }


    protected boolean writeData() throws IOException {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(destinationFile.toFile());
            Source input = new DOMSource(doc);

            transformer.transform(input, output);
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
