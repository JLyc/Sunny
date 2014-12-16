package test_package;

import java.util.ArrayList;

/**
 * Created by sochaa on 11. 12. 2014.
 */
public class MergeList {
	static ArrayList<ArrayList<String>> A = new ArrayList<>();
	static ArrayList<ArrayList<String>> B = new ArrayList<>();

	public static void main(String[] args) {





		A.add(new ArrayList<String>());
		A.get(0).add("a");
		A.get(0).add("b");
		A.add(new ArrayList<String>());
		A.get(1).add("d");

		B.add(new ArrayList<String>());
		B.get(0).add("a");
		B.get(0).add("c");

		System.out.println(A);
		System.out.println(B);

		recursive(A, B);
//
// for(ArrayList<Object> listA : A) {
//			if(!B.contains(listA))
//				B.add(listA);
//		}

		System.out.println(A);
		System.out.println(B);
	}

	private static void recursive(ArrayList a,ArrayList<?> c)
	{
		a.retainAll(c);
	}

	private static void recursiveA(ArrayList<?> a,ArrayList<?> c)
	{
//		for(Objects abc : c)
		{

//			recursiveA(abc);

		}
	}
}
