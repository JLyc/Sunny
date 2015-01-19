package neural_center.initialization;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by JLyc on 14.10.2014.
 * <p>
 * Dependencies:         Independent use
 */
public class EnvironmentOfOS {
	private static EnvironmentOfOS INSTANCE;
	private static final Map<String, String> environmentProperties = new HashMap<>();

	private EnvironmentOfOS() {

		try{
			loadProperties();
			System.out.println("Environment of OS load successful: " + testClass());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void loadProperties() {
		int i = 0; try {
			environmentProperties.put("os", getOsProperty());
			environmentProperties.put("user", System.getProperty("user.name"));
			environmentProperties.put("listeningConfig", getPropertyBasedOnEnvironment()[i++]);
			environmentProperties.put("grammarForListening", getPropertyBasedOnEnvironment()[i++]);
			environmentProperties.put("commandsSource", getPropertyBasedOnEnvironment()[i++]);
			environmentProperties.put("recognizedWords", getPropertyBasedOnEnvironment()[i++]);
			environmentProperties.put("commandExecutor", getPropertyBasedOnEnvironment()[i++]);
			environmentProperties.put("executorParameter", getPropertyBasedOnEnvironment()[i++]);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Internal error in environment. Cant work without environment");
			SunnyInitialization.turnOffSunny(1);
		}
	}

	private String getOsProperty() {
		String fullOSName = System.getProperty("os.name"); if(fullOSName.matches(".*Windows.*")) return "Windows";
		if(fullOSName.matches(".*Linux.*")) return "Linux"; return null;
	}

	private String[] getPropertyBasedOnEnvironment() throws Exception {
		String[] output = new String[6]; int i = 0; switch(environmentProperties.get("os")) {
			case "Linux": output[i++] = ""; output[i++] = ""; output[i++] = ""; output[i++] = "recognizedWords.xml"; output[i++] = "/bin/sh"; output[i++] = "-c"; return output;
			case "Windows": output[i++] = "sunny_windows.config.xml"; output[i++] = "sunny_windows_gram.xml"; output[i++] = "commands_windows.xml"; output[i++] = "recognizedWords.xml"; output[i++] = "cmd"; output[i++] = "/c"; return output;
			case "xos": output[i++] = "configs/sunny_xos.config.xml"; output[i++] = "gramFiles/sunny_xos.gram"; output[i++] = "dataFile/commands_xos.txt"; output[i++] = "recognizedWords.xml"; output[i++] = ""; output[i++] = ""; throw new Exception("xos not implemented");
			default: {
				throw new Exception("unknown os architecture");
			}
		}
	}

	/**
	 * @param property
	 * <table style="width:10%">
	 *  <tr><td>os</td></tr>
	 *  <tr><td>user</td></tr>
	 *  <tr><td>listeningConfig</td></tr>
	 *  <tr><td>grammarForListening</td></tr>
	 *  <tr><td>commandsSource</td></tr>
	 *  <tr><td>recognizedWords</td></tr>
	 *  <tr><td>commandExecutor</td></tr>
	 *  <tr><td>executorParameter</td></tr>
	 * </table>
	 * @return string name of property
	 */
	public static String getProperties(String property) {
		return environmentProperties.get(property);
	}

	public static EnvironmentOfOS enforceInitialization() {
		if(INSTANCE == null) INSTANCE = new EnvironmentOfOS();

		return INSTANCE;
	}


	private boolean testClass() throws NoSuchFieldException{
		boolean isSuccessful = true;
		for(Map.Entry<String, String> mapElement : environmentProperties.entrySet()) {
			if(mapElement.getValue().matches(".*\\.txt")&&this.getClass().getClassLoader().getResourceAsStream(mapElement.getValue())==null) {
				throw new NoSuchElementException("Can't load file: " + mapElement);
			}
		} return isSuccessful;
	}
}
