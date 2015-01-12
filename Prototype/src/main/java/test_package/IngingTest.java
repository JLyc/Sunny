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
	}
}
