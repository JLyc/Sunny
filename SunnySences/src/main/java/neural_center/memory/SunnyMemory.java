package neural_center.memory;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.memory.initialize_memory.helpers.BufferFileToSunnyMemory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SunnyMemory extends BufferFileToSunnyMemory {
    private static SunnyMemory INSTANCE;

    /**
     * Define dependencies here
     */
    static {
        EnvironmentOfOS.enforceInitialization();
    }

    private static final Map<String, Path> brainStructure = new HashMap<>();
    private static final Path DEFFAULT_PATH = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "Brain", EnvironmentOfOS.getProperties("os"));

    public static final HandleMemory handleMemory = new HandleMemory();

    String[] brainParts = {"Persistent","Temporary","Action"};



     public SunnyMemory() {
         try {
             constructMemoryPaths();
             System.out.println("Environment of OS load successful: " + testMemoryForFaults());
         } catch(Exception e) {

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

    public static Path getPathInMemory(String key) {
        return brainStructure.get(key);
    }

    public boolean testMemoryForFaults() throws IOException {
        for(String name : brainParts){
            verifyPath(brainStructure.get(name));
        }
        return true;
    }

    public static SunnyMemory enforceInitialization(){
        if(INSTANCE == null)
            INSTANCE = new SunnyMemory();

        return INSTANCE;
    }

    public static void main(String[] args) {
        EnvironmentOfOS.enforceInitialization();
        SunnyMemory.enforceInitialization();
    }
}