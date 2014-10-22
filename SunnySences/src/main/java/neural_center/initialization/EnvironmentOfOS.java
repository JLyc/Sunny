package neural_center.initialization;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by socha on 14.10.2014.
 */
public class EnvironmentOfOS implements StaticBlockExecution {
    public static String loadStaticBlock = "loading";
    private static final Map<String, String> environmentProperties = new HashMap<>();

    public static String theName = "The Loader";

//    static {
//        System.out.println("Loader.static");
//    }

    static{
        System.out.println("in static block");
        int i = 0;
        try {
            environmentProperties.put("os", System.getProperty("os.name"));
            environmentProperties.put("user", System.getProperty("user.name"));
            environmentProperties.put("listeningConfig", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("grammarForListening", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("commandsSource", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("commandExecutor", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("executorParameter", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("RecognizedWords", "recognizedWords.txt");

        } catch (Exception e) {
            System.err.println("Internal error in environment. Cant work without environment");
            SunnyInitialization.turnOffSunny(1);
        }
    }

    private static String[] getPropertyBasedOnEnvironment() throws Exception
    {
        String[] output = new String[5];
        int i = 0;
        switch (environmentProperties.get("os")) {
            case "Linux":
                output[i++] = "sunny_linux.config.xml";
                output[i++] = "sunny_linux.gram";
                output[i++] = "Commands_linux.txt";
                output[i++] = "/bin/sh";
                output[i++] = "-c";
                return output;
            case "Windows":
                output[i++] = "sunny_windows.config.xml";
                output[i++] = "sunny_windows.gram";
                output[i++] = "commands_windows.txt";
                output[i++] = "cmd";
                output[i++] = "/c";
                return output;
            case "xos":
                output[i++] = "configs/sunny_xos.config.xml";
                output[i++] = "gramFiles/sunny_xos.gram";
                output[i++] = "dataFile/commands_xos.txt";
                output[i++] = "";
                output[i++] = "";
                throw new Exception("xos not implemented");
            default: {
                throw new Exception("unknown os architecture");
            }
        }
    }

    public static String getProperties(String property) {
        return environmentProperties.get(property);
    }
}
