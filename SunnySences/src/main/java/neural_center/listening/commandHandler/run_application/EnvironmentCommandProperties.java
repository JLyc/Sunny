package neural_center.listening.commandHandler.run_application;

import neural_center.speaking.SpeakingAdapter;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class EnvironmentCommandProperties
{
	private String commandExecutor;
	private String commandModifier;
	private String commandUser;
	
	public EnvironmentCommandProperties()
	{
        String property = System.getProperty("os.name");
		if(property.equalsIgnoreCase("Linux"))
		{
			commandExecutor = "/bin/sh";
			commandModifier = "-c";
			commandUser = System.getProperty("user.name");
		}
		else if(property.equalsIgnoreCase("Windows"))
		{
			commandExecutor = "cmd";
			commandModifier = "/c";
			commandUser = "not implemented";
		}
	}
	
	public String getCommandExecutor()
	{
		return commandExecutor;
	}
	
	public String getCommandModifier()
	{
		return commandModifier;
	}
	
	public String getCommandUser()
	{
		return commandUser;
	}

    //TODO fix this privacy policy issue
	public String hardCoded()
	{
		return "171189";
	}


    public byte[] getSudo() {
        SpeakingAdapter.getInstance().say("Enter sudo password");
        String password = JOptionPane.showInputDialog(null, "Enter sudo password", "Password");

        return (password+"\n").getBytes();
    }

    private File file;
    public byte[] getSudoCANDIDATE() {
        byte[] output = new byte[0];

        try {
            if (file == null) {
                file = File.createTempFile("help", Long.toString(new Date().getTime()));
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(JOptionPane.showInputDialog(null, "Enter sudo password", "Password"));
            }
            output = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return output;
        }

    }
}
