package test_package;


public class RegExTest
{
    private static String[][] word = {
            {"sunny run fire fox"},
            {"nieco.txt"}
    };

    private static String[][] regex = {
            {""},
            {".*\\.txt"},
            {".*\\..{3}"}
    };

	public static void main(String arg[])
	{
        System.out.println(word[1][0].matches(regex[2][0]));
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
//
//		String redLine = "sudo pkill -u %user% chrome";
//		System.out.println(redLine.contains("sudo"));
//		if(redLine.matches(".+%user%.+"))
//		{
//			String[] lineSplit = redLine.split("%user%");
//			String user = "lyc";
//			System.out.println(lineSplit[0]+user+lineSplit[1]);
//		}
//		System.out.println(redLine.replace("%user%", "lyc"));
////
//		String redLine = "Windows 7";
////		System.out.println(redLine.contains("sudo"));
//		if(redLine.matches(".*Windows.*"))
//		{
////			String[] lineSplit = redLine.split("%user%");
////			String user = "lyc";
//			System.out.println(redLine.matches(".+Windows.+"));
//		}
//		System.out.println(redLine.replace("%user%", "lyc"));

//        String[] splitWord = "|1|Run|Show|Start|Turn on|Mute|".split("\\|");
//        				for (int wordNo = 0; wordNo < splitWord.length; wordNo++) {
//
//                            System.out.println(splitWord[wordNo] + " " + Integer.parseInt(splitWord[1]));
//                        }

//        String line = "|1|Run|Show|Start|Turn on|Mute|";
//        for (String split : line.split("\\|")) {
//            System.out.println(split);;
//        }

//        String line = "Fire Fox";
//        String[] p = {"Fire", "Fox"};
//        System.out.println(line.matches(p[0]+".+"));
//        System.out.println(line.matches(".+"+p[1]));

//        String preced = line.substring(line.indexOf("(")+1, line.lastIndexOf(")"));
//        String[] out = preced.split("\\)\\s\\(");
//        for (String o:out) {
//            for (String a :o.split("\\s\\|\\s")) {
//                System.out.println(a);
//            }
//        }
    }

}
