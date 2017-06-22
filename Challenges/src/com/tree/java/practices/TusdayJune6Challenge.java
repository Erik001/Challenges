package com.tree.java.practices;

public class TusdayJune6Challenge {

	public static void main(String[] args) {
		int a = 42;
		int b = 43;
		int c = 44;
		int d = 45;
		System.out.println(new TusdayJune6Challenge().imagine(a, b, c, d)[0]);

	}

	int[] imagine(int a, int b, int c, int d) {
	     int[] resultArray  = {a*c - b*d,a*d + b*c};//new int[2];
	     //resultArray[0] = a*c - b*d;
	    // resultArray[1] = a*d + b*c;
	     return resultArray;
	}

}
