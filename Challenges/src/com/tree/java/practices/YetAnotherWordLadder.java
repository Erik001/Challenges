package com.tree.java.practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class YetAnotherWordLadder {
	public static boolean found;
	public static boolean hasPassedStartingPoint;
	public static Map<List<String>, Boolean> transformationPaths = new HashMap<>();
	private static boolean isSamelevel;

	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		String wordList[] = { "hot", "dot", "dog", "lot", "log", "cog" };
		// int shortestTrans = 0;
		System.out.println(wordLadder(beginWord, endWord, wordList));

	}

	public static int wordLadder(String beginWord, String endWord, String[] wordList) {
		int shortestTrans = 0;
		List<String> wordsArrayList = new ArrayList<>(Arrays.asList(wordList));
		if (beginWord.length() != endWord.length() || !wordsArrayList.contains(endWord)) {
			return shortestTrans;
		}

		Queue<List<String>> transList = new LinkedList<>();
		List<List<List<String>>> levels = new LinkedList<>();
		Map<Integer, List<String>> mapOfDiffs = null;
		transList.add(new LinkedList<>(Arrays.asList(beginWord)));
		int levelId = 0;
		while (!transList.isEmpty()) {
			List<List<String>> level = new ArrayList<>(transList.size());
			levels.add(level);
			++levelId;
			for (List<String> strList : transList) {
				level.add(strList);
				System.out.printf("Level %d: ", levelId);
				System.out.println(levels);
				if (found)
					return levels.size();
				List<List<String>> singleLevelList = new ArrayList<>(strList.size());
				for (String str : new ArrayList<>(strList)) {
					if (str.equals(beginWord))
						mapOfDiffs = getDiffsMap(str, wordsArrayList);
					else {
						mapOfDiffs = getDiffsMap(str, wordsArrayList);
					}

					if (mapOfDiffs.get(1) != null && mapOfDiffs.get(1).size() > 0) {
						singleLevelList.add(mapOfDiffs.get(1));
						if (mapOfDiffs.get(1).contains(endWord)) {
							System.out.println("Found you!!");
							found = true;
							break;
						}
					} else {
						break;
					}
				}
				transList.addAll(singleLevelList);
				wordsArrayList = getRemainingStrings(wordsArrayList, transList.peek());
				transList.poll();

			}

		}
		return shortestTrans;

	}

	private static List<String> getRemainingStrings(List<String> wordList, List<String> list) {
		wordList.removeAll(list);
		Collections.sort(wordList);
		return wordList;
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
