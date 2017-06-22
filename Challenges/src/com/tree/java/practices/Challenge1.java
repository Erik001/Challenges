package com.tree.java.practices;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Challenge1 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new Challenge1().thatsOdd("It's not true"));
	}

	boolean thatsOdd5(String str) {
		if (str != null && str.length() >= 0 && str.length() < 1000 && str.length() % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}

	boolean thatsOdd2(String str) {
		List<String> toRemove = Arrays.asList(",", "-", "'");
		StringBuilder result = new StringBuilder();

		if (str != null && str.length() >= 0 && str.length() < 1000) {
			str.chars().mapToObj(i -> (char) i).filter(c -> Character.isLowerCase(c) | c == '?')
					.forEach(c -> result.append(c));
			str = result.toString();
			System.out.println("\"" + str + "\"");
			if (!(str.length() % 2 == 0))
				return true;
			else
				return false;
		} else {
			return false;
		}

	}

	boolean thatsOdd3(String str) {
		StringBuilder result = new StringBuilder();

		if (str != null && str.length() >= 0 && str.length() < 1000) {
			str.chars().mapToObj(i -> (char) i).filter(c -> Character.isLowerCase(c) | c == '?' // |
																								// c
																								// ==
																								// '\''
					| c == '+' | c == '#' | c == '{' | c == '}' | c == '-').forEach(c -> result.append(c));
			str = result.toString();
			System.out.println("\"" + str + "\"");
			if (!(str.length() % 2 == 0))
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	boolean thatsOdd(String str) throws UnsupportedEncodingException {
		byte[] bytes = str.getBytes("US-ASCII");
		int sum = 0;
		for (int i = 0; i < bytes.length; i++) {
			sum += bytes[i];
		}
		if (sum % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}
}
