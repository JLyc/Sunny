package neural_center.memory;

import neural_center.initialization.EnvironmentOfOS;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import uniqe_skills.PerformanceTest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Memory extends BufferFileToMemory {
    private static Memory INSTANCE;

    private static final Map<String, Path> brainStructure = new HashMap<>();
    private static final Path DEFAULT_PATH = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "Brain", EnvironmentOfOS.getInstance().getProperties("os"));
    private static final String[] brainParts = {"Persistent","Temporary","Action"};

    private static final String[] loadToMemory = {
            "grammarForListening",
            "recognizedWords",
            "commandsSource",
            "listeningConfig",
    };

    private Memory() {
         try {
             constructMemoryPaths();
             verifyFileExistence();
             System.out.println("Memory \t\t\t\t load successful: " + testMemoryForFaults());
             PerformanceTest.result();
         } catch(Exception e) {
            e.printStackTrace();
             System.err.println("Problem with memory constructor");
         }
     }

    private void verifyFileExistence() throws IOException {
        for(String pathString : loadToMemory){
            Path filePath = DEFAULT_PATH.resolve("Persistent").resolve(EnvironmentOfOS.getInstance().getProperties(pathString));
            if(Files.notExists(filePath) || isEmpty(filePath)) {
                try {
                    createFileFromResources(pathString,filePath);
                } catch (ParserConfigurationException | TransformerException | SAXException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isEmpty(Path filePath) {
        try(InputStream stream = filePath.toUri().toURL().openStream()) {
            if(stream.available()>0){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public void createFileFromResources(String resourcesKey, Path destinationFile) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(Memory.class.getClassLoader().getResourceAsStream(EnvironmentOfOS.getInstance().getProperties(resourcesKey)));
        doc.getDocumentElement().normalize();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(destinationFile.toFile());
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
    }

    private void constructMemoryPaths() throws IOException {
        for(String name : brainParts){
            brainStructure.put(name, DEFAULT_PATH.resolve(name));
            verifyPath(brainStructure.get(name));
        }
    }

    private boolean verifyPath(final Path path) throws IOException {
        if(Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectories(path);
            verifyPath(path);
        }
        return true;
    }

    /**
     * @param directory <table style="width:10%">
     *                 <tr><td>Persistent</td></tr>
     *                 <tr><td>Temporary</td></tr>
     *                 <tr><td>Action</td></tr>
     *                 </table>
     * @return Path to given directory
     */
    public Path getPathInMemory(String directory) {
        return brainStructure.get(directory);
    }

    public boolean testMemoryForFaults() throws FileNotFoundException {
        for(String name : brainParts){
            if(Files.notExists(brainStructure.get(name), LinkOption.NOFOLLOW_LINKS)){
                throw new FileNotFoundException(name);
            }
        }
        return true;
    }

    public synchronized static Memory getInstance(){
        if(INSTANCE == null) INSTANCE = new Memory();
        return INSTANCE;
    }
}
