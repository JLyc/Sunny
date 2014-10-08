package test_package;

public class IngingTest
{
	public static void main(String args[])
	{
		String word = "Trun";
		
		String wordup = word.replaceFirst("\\s", "ing ");
		if(word.equals(wordup))
		{
			wordup = word+"ing";
		}
		System.out.println(wordup);
		String[] splits = word.split("\\s");
		
		for(int i=0;i<splits.length;i++)
		{
			System.err.println(splits[i]+"\n");
		}
		
		
		//		System.out.println(splits[0]+"ing"+splits[1]);
	}
}
