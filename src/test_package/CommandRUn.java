package test_package;

import java.io.IOException;


public class CommandRUn {
    public static void main(String arg[]) {

        //		String command = "sudo -S pm-suspend";
        String command = "exec firefox";
        try {
            //			byte[] pwd =  (171189)
            System.out.println(System.getProperty("os.name"));
            System.out.println(System.getProperty("user.name"));
            Process p = new ProcessBuilder("/bin/sh", "-c", command).start();
            //					p.getOutputStream().write(("171189"+"\n").getBytes());
            //					p.getOutputStream().flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //		String s;
        //		Process p;
        //		try
        //		{
        //			p = Runtime.getRuntime().exec("ls -aF");
        //			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        //			while ((s = br.readLine()) != null)
        //			{
        //				System.out.println("line: " + s);
        //			}
        //			p.waitFor();
        //			System.out.println("exit: " + p.exitValue());
        //			p.destroy();
        //		}
        //		catch (Exception e)
        //		{}
    }
}
