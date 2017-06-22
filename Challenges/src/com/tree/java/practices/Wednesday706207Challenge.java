package com.tree.java.practices;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Wednesday706207Challenge {

	public static void main(String[] args) {
		System.out.println(new Wednesday706207Challenge().reverseInverse5("This is; an$ example, for: my Friends"));

	}

	String reverseInverse(String s) {

		/*
		 * char [] myStringChars = s.toCharArray(); Pattern p =
		 * Pattern.compile("\\w+"); Matcher m = p.matcher(s); while(m.find()){
		 * System.out.println(s.substring(m.start(), m.end())); }
		 * 
		 * return s;
		 */
		return Pattern.compile(" +").splitAsStream(s).map(word -> new StringBuilder(word).reverse())
				.collect(Collectors.joining(" "));

	}

	String reverseInverse2(String s) {

		return Pattern.compile(":|$|;|!|\\.|,| +").splitAsStream(s).map((word) -> {
			StringBuilder sb = new StringBuilder(word).reverse();
			StringBuilder sb2 = new StringBuilder(sb.length());
			sb.chars().mapToObj(c -> (char) c).forEach(
					c -> sb2.append((Character.isUpperCase(c)) ? Character.toLowerCase(c) : Character.toUpperCase(c)));
			return sb2;
		}).collect(Collectors.joining(" "));

	}

	String reverseInverse3(String input) {
		//
		// Regular expression to match characters that should split the string.
		// ! or . or , or 'a sequence of whitespace'
		//
		String specialCharExpression = ":|$|;|!|\\.|,| *";
		StringBuilder result = new StringBuilder();
		int fragmentStart = 0;
		for (int i = 0; i < input.length(); i++) {
			String currentChar = input.charAt(i) + "";
			if (currentChar.matches(specialCharExpression) || i == input.length() - 1) {
				result.append(new StringBuilder(input.substring(fragmentStart, i)).reverse().toString());
				result.append(currentChar);
				fragmentStart = i + 1;
			}
		}

		return result.toString();
	}
	
	String reverseInverse4(String input) {
		//
		// Regular expression to match characters that should split the string.
		// ! or . or , or 'a sequence of whitespace'
		//
		String specialCharExpression = ":|$|;|!|\\.|,| *";
		StringBuilder result = new StringBuilder();
		int fragmentStart = 0;
		for (int i = 0; i < input.length(); i++) {
			String currentChar = input.charAt(i) + "";
			if (currentChar.matches(specialCharExpression) || i == input.length() - 1) {
				result.append(new StringBuilder(input.substring(fragmentStart, i)).reverse().toString());
				result.append(currentChar);
				fragmentStart = i + 1;
			}
		}

		return result.toString();
	}
	
	int i, j, h;
	String reverseInverse5(String s) {
	    String r = "";
	    for (String w : s.split("\\b"))
	        if (w.matches("\\W+"))
	            r += w;
	        else
	            for (i = j = w.length(); j > 0;)
	                r += (char) 
	                    (((h = w.charAt(i - j) / 32) ^ h / 2) * 32 | 
	                    w.charAt(--j) & 31);
	    return r; 
	}
	
	String reverseInverse6(String x) {
	    String s = "";

	    for (String y: x.split("(?!^)\\b")) {
	        int j = 0, t;
	        for (int c: y.getBytes())
	            s += (char)((t = y.charAt(y.length() - ++j)) > 96? c > 96? t-32: t:
	                 t > 64? c < 96? t+32: t:
	                 c < 48 | c > 57? c: t);
	    }

	    return s;
	}

}
