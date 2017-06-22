package com.tree.java.practices;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryTreeOperations {
	private static final char L_PAREN = '(';
	private static final char R_PAREN = ')';
	private static final char L_BRACE = '{';
	private static final char R_BRACE = '}';
	private static final char L_BRACKET = '[';
	private static final char R_BRACKET = ']';

	private int treeLevel;
	private int sumLevel;
	private boolean isDigitFound;
	private String nodeValue;

	public boolean isBalanced(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == L_PAREN) {
				stack.push(L_PAREN);
				if (isDigitFound)
					System.out.printf("I've the value %d.%n", Integer.parseInt(nodeValue));
				System.out.printf("I'm in level %d.%n", treeLevel);
				++treeLevel;
				isDigitFound = false;
				nodeValue = "";

			} else if (s.charAt(i) == L_BRACE)
				stack.push(L_BRACE);

			else if (s.charAt(i) == L_BRACKET)
				stack.push(L_BRACKET);

			else if (s.charAt(i) == R_PAREN) {
				if (stack.isEmpty())
					return false;
				if (stack.pop() != L_PAREN)
					return false;
				--treeLevel;
				isDigitFound = false;
			}

			else if (s.charAt(i) == R_BRACE) {
				if (stack.isEmpty())
					return false;
				if (stack.pop() != L_BRACE)
					return false;
			}

			else if (s.charAt(i) == R_BRACKET) {
				if (stack.isEmpty())
					return false;
				if (stack.pop() != L_BRACKET)
					return false;
			}

			else if (Character.isDigit(s.charAt(i))) {
				isDigitFound = true;
				nodeValue += s.charAt(i);
			}

			// ignore all other characters

		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		String s = "(0(5(6()())(14()(9()())))(7(1()())(23()())))";
		// System.out.println(new BinaryTreeOperations().isBalanced(s));
		System.out.printf("The sum of the nodes in level %d is %d.%n", 2,
				new BinaryTreeOperations().treeLevelSum5(s, 2));
	}

	public int treeLevelSum(String tree, int k) {
		for (int i = 0; i < tree.length(); i++) {

			if (tree.charAt(i) == L_PAREN) {
				if (isDigitFound) {
					// System.out.printf("I'm value
					// %d.%n",Integer.parseInt(nodeValue));
					if (treeLevel == k + 1)
						sumLevel += Integer.parseInt(nodeValue);
				}
				// System.out.printf("I'm level %d.%n",treeLevel);
				treeLevel++;
				isDigitFound = false;
				nodeValue = "";

			}

			else if (tree.charAt(i) == R_PAREN) {
				treeLevel--;
				isDigitFound = false;
			}

			else if (Character.isDigit(tree.charAt(i))) {
				isDigitFound = true;
				nodeValue += tree.charAt(i);

			}

		}
		return sumLevel;
	}

	int treeLevelSum2(String tree, int k) {
		int depth = 0;
		int temp = 0;
		int sum = 0;
		for (char cha : tree.toCharArray()) {
			if (cha == '(') {
				depth++;
				sum += temp;
				temp = 0;
			} else if (cha == ')') {
				depth--;
				sum += temp;
				temp = 0;
			} else if (depth == k + 1) {
				temp = temp * 10 + cha - '0';
			}
		}
		return sum;
	}

	int treeLevelSum3(String tree, int k) {
		int n = 0;
		int level = -1;
		int sum = 0;
		for (char c : tree.toCharArray()) {
			if (c == '(') {
				if (k == level) {
					sum += n;
				}
				level++;
				n = 0;
				continue;
			}
			if (c == ')') {
				if (k == level) {
					sum += n;
				}
				level--;
				n = 0;
				continue;
			}
			n = n * 10 + c - '0';
		}
		return sum;
	}

	int offset = 0;

	int readInt(String str) {
		int mult = 1;
		if (str.charAt(offset) == '-') {
			mult = -1;
			offset++;
		}
		int result = 0;
		while (offset < str.length()) {
			final char ch = str.charAt(offset);
			if (ch >= '0' && ch <= '9') {
				result = result * 10 + (ch - '0');
				offset++;
			} else {
				break;
			}
		}

		return mult * result;
	}

	int treeLevelSum4(String tree, int k) {

		offset++;
		final int value = readInt(tree);

		int left = 0, right = 0;
		if (offset < tree.length() && tree.charAt(offset) == '(') {
			left = treeLevelSum4(tree, k - 1);
		}

		if (offset < tree.length() && tree.charAt(offset) == '(') {
			right = treeLevelSum4(tree, k - 1);
		}

		offset++;

		return (k == 0 ? value : 0) + left + right;
	}
	
	
	int treeLevelSum5(String tree, int k) {
	    int[] level = new int[tree.length()];
	    int sum = 0;
	    int l = 0;
	    int r = 0;
	    Pattern p = Pattern.compile("\\d+");
	    Matcher m = p.matcher(tree);
	    int i = 0;
	    for(char c : tree.toCharArray()){
	        if(c=='(')
	            l++;
	        else if(c==')')
	            r++;
	        level[i] = l - r;
	        i++;
	    }
	    while(m.find()){
	        if(level[m.start()] == k+1)
	            sum += Integer.parseInt(tree.substring(m.start(), m.end())); 
	    }
	    return sum;
	}
	
	
	int treeLevelSum6(String tree, int k) {

	    int sum = 0;
	    int level = 0;
	    int i = 1;
	    
	    while(i < tree.length()){
	        char c = tree.charAt(i++);
	        if(Character.isDigit(c)){
	            int num = Character.getNumericValue(c);
	            while(true){            
	                c = tree.charAt(i++);
	                if(Character.isDigit(c)){
	                    num *= 10;
	                    num += Character.getNumericValue(c);
	                } else
	                    break;
	            }
	            if(level == k)
	                sum+=num;
	        }
	        if(c == '(')
	            level++;
	        else if(c == ')')
	            level--;
	    }
	    
	    return sum;
	}


}
