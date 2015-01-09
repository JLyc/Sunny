package neural_center.initialization;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JLyc on 14.10.2014.
 *
 * Dependencies:         Independent use
 */
public class EnvironmentOfOS {
    private static EnvironmentOfOS INSTANCE;
    private static final Map<String, String> environmentProperties = new HashMap<>();

    static{
        INSTANCE = new EnvironmentOfOS();
        int i = 0;
        try {
            if(System.getProperty("os.name").matches(".*Windows.*"))
            {
                environmentProperties.put("os", "Windows");
            }
            else
            {
                environmentProperties.put("os", System.getProperty("os.name"));
            }
            environmentProperties.put("user", System.getProperty("user.name"));
            environmentProperties.put("listeningConfig", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("grammarForListening", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("commandsSource", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("commandExecutor", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("executorParameter", getPropertyBasedOnEnvironment()[i++]);
            environmentProperties.put("recognizedWords", "recognizedWords.xml");
            SunnyInitialization.setStateOkFor(INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Internal error in environment. Cant work without environment");
            SunnyInitialization.turnOffSunny(1);
        }
    }

    private EnvironmentOfOS(){}

    private static String[] getPropertyBasedOnEnvironment() throws Exception
    {
        String[] output = new String[5];
        int i = 0;
        switch (environmentProperties.get("os")) {
            case "Linux":
                output[i++] = "";
                output[i++] = "";
                output[i++] = "";
                output[i++] = "/bin/sh";
                output[i++] = "-c";
                return output;
            case "Windows":
                output[i++] = "sunny_windows.config.xml";
                output[i++] = "sunny_windows_gram.xml";
                output[i++] = "commands_windows.xml";
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

    public static void enforceInitialization(){}

    public static void main(String[] args) {
        INSTANCE.testPath();
    }

    private void testPath() {
        InputStream ip = this.getClass().getClassLoader().getResourceAsStream("commands_windows.xml");
        if(ip==null)
        {
            System.out.println("fail");
        }
    }
}
