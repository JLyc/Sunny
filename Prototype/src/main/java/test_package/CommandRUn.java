package test_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandRUn
{
	private static String command[][] = {{"nircmd.exe cdrom open"}};
	private static int commandNumber = 0;

	public static void main(String arg[]) {




		try {
			//			byte[] pwd =  (171189)
			System.out.println(System.getProperty("os.name"));
			System.out.println(System.getProperty("user.name"));

			Process p = new ProcessBuilder("S:\\JLyc\\Sunny\\nircmd", "/c", command[commandNumber][0]).start();
			//					p.getOutputStream().write(("171189"+"\n").getBytes());
			//					p.getOutputStream().flush();
			output(p);
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		private static void output(Process p)
	{
		String s;
			try
			{
				p = Runtime.getRuntime().exec("ls -aF");
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
				{
					System.out.println("line: " + s);
				}
				p.waitFor();
				System.out.println("exit: " + p.exitValue());
				p.destroy();
			}
			catch (Exception e)
			{}
	}
}
