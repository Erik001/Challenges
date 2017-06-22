package com.tree.java.codefights;

public class ChallengeJune162017 {

	public static void main(String[] args) {
		String givenString = "Welcome to CodeFights!";
		String value = "Back to school";

		System.out.println(new ChallengeJune162017().notIndexOf2(givenString, value));
	}

	int notIndexOf(String g, String v) {
		return g.replaceAll(v.replaceAll("(.)", "[^$1]"), "	").indexOf("	");
	}

	int notIndexOf2(String s, String v) {
		int i = s.split(v.replaceAll(".", "[^$0]"))[0].length();
		return i < s.length() ? i : -1;

		// Pattern p = Pattern.compile(v.replaceAll(".", "[^$0]"));
		// Matcher m = p.matcher(s);
		// return m.find() ? m.start() : -1;
	}

	int i, j, l;

	int notIndexOf3(String g, String v) {
		for (l = v.length(); i + l < g.length() & ++j > 0; i++)
			for (j = l; j-- > 0 && g.charAt(i + j) != v.charAt(j);)
				;
		return (i >> j) - 1;
	}

}
