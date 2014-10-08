package test_package;


public class RegExTest {
    public static void main(String arg[]) {

        //		String slovo = "sunny run fire fox";
        //		String test = "unhappy";
        //		slovo = test.substring(2);
        //		System.out.println(test);
        //		slovo.substring(5);
        //		System.out.println(slovo.matches("sunny run .+"));
        //		//		System.out.println(slovo.substring((slovo.inde));
        //		System.out.println(slovo.matches(".*sunny.+"));

        //		String redLine = "// modifier // words\n|1|Run|Show|Start|Turn on|Mute|\n|2|Close|Cancel|End|Turn off|Unmute|\n|0|volume|\n|-1|Sleep|";
        //		String[] lineSplit = redLine.split("\\n");
        //
        //		for (int line = 0; line < lineSplit.length; line++)
        //		{
        //			if (lineSplit[line].matches("\\|.+"))
        //			{
        //				String[] splitWord = lineSplit[line].split("\\|");
        //				for (int wordNo = 2; wordNo < splitWord.length; wordNo++)
        //				{
        //					System.out.println(splitWord[wordNo]+ " "+ Integer.parseInt(splitWord[1]));
        //				}
        //			}
        //		}
        //String line = (String) command.subSequence(1, command.length()-1);
        //		String[] breakLine = line.split("\\) \\(");
        //		String[] break1 = breakLine[1].split("\\s\\|\\s");
        //		for(String b : break1)
        //		{
        //			System.out.println(b);
        //
        //		}

        String redLine = "sudo pkill -u %user% chrome";
        System.out.println(redLine.contains("sudo"));
        if (redLine.matches(".+%user%.+")) {
            String[] lineSplit = redLine.split("%user%");
            String user = "lyc";
            System.out.println(lineSplit[0] + user + lineSplit[1]);
        }
        System.out.println(redLine.replace("%user%", "lyc"));

    }
}
