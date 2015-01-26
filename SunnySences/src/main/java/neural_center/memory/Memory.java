package neural_center.memory;

import neural_center.initialization.EnvironmentOfOS;
import uniqe_skills.PerformanceTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
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
            if(Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        }
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
