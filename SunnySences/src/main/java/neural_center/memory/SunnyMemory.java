package neural_center.memory;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.memory.initialize_memory.helpers.BufferFileToSunnyMemory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SunnyMemory extends BufferFileToSunnyMemory {
    private static SunnyMemory INSTANCE;

    private static final Map<String, Path> brainStructure = new HashMap<>();
    private static final Path DEFFAULT_PATH = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "Brain", EnvironmentOfOS.getProperties("os"));

    String[] brainParts = {"Persistent","Temporary","Action"};



     public SunnyMemory() {
         try {
             constructMemoryPaths();
             System.out.println("Memory load successful: " + testMemoryForFaults());
         } catch(Exception e) {
            e.printStackTrace();
         }
     }

    void constructMemoryPaths() throws IOException {
        for(String name : brainParts){
            brainStructure.put(name, DEFFAULT_PATH.resolve(name));
            verifyPath(brainStructure.get(name));
        }
    }

    boolean verifyPath(final Path path) throws IOException {
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
    public static Path getPathInMemory(String directory) {
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

    public static SunnyMemory enforceInitialization(){
        if(INSTANCE == null)
            INSTANCE = new SunnyMemory();

        return INSTANCE;
    }
}
