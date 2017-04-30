package com.tree.java.practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLadderRemastered {
	public static int shortestTrans = 0;
	public static boolean found;
	public static Map<Integer, Boolean> foundPaths = new HashMap<>();
	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		String wordList[] = { "hot", "dot", "dog", "lot", "log", "cog" };
		//int shortestTrans = 0;	
		System.out.println(wordLadder(beginWord, endWord, wordList));
		System.out.println(foundPaths);

	}

	public static int wordLadder(String beginWord, String endWord, String[] wordList) {
				
		if (beginWord.length() != endWord.length() || !Arrays.asList(wordList).contains(endWord)) {
			return 0;
		}
		Map<Integer, List<String>> mapOfDiffs = getDiffsMap(beginWord, Arrays.asList(wordList));
		
		if (mapOfDiffs.get(1) != null && mapOfDiffs.get(1).size() > 0 && mapOfDiffs.get(1).contains(endWord)) {
			++shortestTrans;
			System.out.println(shortestTrans);
			found = true;
			foundPaths.put(shortestTrans, found);
			System.out.println("Found!!");
			shortestTrans = 0;
			return shortestTrans;
		} else if (mapOfDiffs.get(1) != null && mapOfDiffs.get(1).size() > 0) {
			String[] remainingStr = getRemainingStrings(mapOfDiffs);
			for (String str : mapOfDiffs.get(1)) {
				++shortestTrans;
				System.out.println("Map with Diffs:" + mapOfDiffs);
				System.out.println("find: " + str + " in : " + Arrays.asList(remainingStr));
				wordLadder(str, endWord, remainingStr);
			}

			return shortestTrans;
		} else {
			System.out.println("Not Found!!");
			return 0;
		}
	}

	private static String[] getRemainingStrings(Map<Integer, List<String>> mapOfDiffs) {
		List<String> strList = new ArrayList<>();
		List<String> remainingStr = new ArrayList<>();
		for (Map.Entry<Integer, List<String>> mapString : mapOfDiffs.entrySet()) {
			if (mapString.getKey() >= 1) {
				strList = mapString.getValue();
				remainingStr.addAll(strList);
			}
		}
		Collections.sort(remainingStr);
		String[] arrRemainingStr = new String[remainingStr.size()];
		return remainingStr.toArray(arrRemainingStr);
	}

	private static Map<Integer, List<String>> getDiffsMap(String beginWord, List<String> wordList) {
		List listOfStrings = null;
		Map<Integer, List<String>> mapOfDiffs = new HashMap<>();
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
		return mapOfDiffs;
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
