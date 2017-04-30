package com.tree.java.practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLadder {

	public static int shortestTrans = 0;
	public static Map<Integer, Boolean> foundPair = new HashMap<>();
	public static Boolean found = false;

	public static void main(String[] args) {
//		String beginWord = "ai";
//		String endWord = "dg";
//		String wordList[] = { "ab", "ai", "ci", "bi", "bg", "dg" };
		
		String beginWord = "hit";
		String endWord = "cog";
		String wordList[] = { "hot", "dot", "dog", "lot", "hop", "log", "cog" };
		
		wordLadder(beginWord, endWord, Arrays.asList(wordList));
		System.out.println("Shortest transformation: " + shortestTrans);
	}

	public static void wordLadder(String beginWord, String endWord, List<String> wordList) {
		if (beginWord.length() != endWord.length() || !wordList.contains(endWord)) {
			return;
		}

		Map<Integer, List<String>> mapOfDiffs = new HashMap<>();
		getDiffsMap(beginWord, wordList, mapOfDiffs);

		if (mapOfDiffs.get(1) != null && mapOfDiffs.get(1).size() > 0 && mapOfDiffs.get(1).contains(endWord)) {
			found = true;
			foundPair.put(shortestTrans, found);
			return;
		}

		while (mapOfDiffs.get(1) != null && mapOfDiffs.get(1).size() > 0 && !found) {
			shortestTrans++;
			List<String> strList = new ArrayList<>();
			List<String> remainingStr = new ArrayList<>();
			for (Map.Entry<Integer, List<String>> mapString : mapOfDiffs.entrySet()) {
				if (mapString.getKey() >= 1) {
					strList = mapString.getValue();
					remainingStr.addAll(strList);
				}
			}
			for (String str : mapOfDiffs.get(1)) {
				if (str.equals(endWord))
					return;
				System.out.println("find: " + str + " in : " + remainingStr);
				wordLadder(str, endWord, remainingStr);
			}
		}
	}

	private static void getDiffsMap(String beginWord, List<String> wordList, Map<Integer, List<String>> mapOfDiffs) {
		List listOfStrings = null;
		for (String temWord : wordList) {
			int diffs = numDiffBtwnStrings(beginWord, temWord);
			if (!mapOfDiffs.containsKey(diffs)) {
				listOfStrings = new ArrayList<>();
				listOfStrings.add(temWord);
				mapOfDiffs.put(diffs, listOfStrings);
			} else {
				mapOfDiffs.get(diffs).add(temWord);
			}
		}
	}

	public static int numDiffBtwnStrings(String s1, String s2) {
		int result = s1.length();
		char[] s1Array = s1.toCharArray();
		char[] s2Array = s2.toCharArray();
		if (Arrays.equals(s1Array, s2Array))
			return 0;
		else {
			for (int i = 0; i < s1Array.length; i++) {
				if (s1Array[i] == s2Array[i]) {
					result--;
				}
			}
		}
		return result;
	}

}
