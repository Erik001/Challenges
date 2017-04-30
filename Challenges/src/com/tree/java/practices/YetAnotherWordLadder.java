package com.tree.java.practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class YetAnotherWordLadder {
	public static boolean found;

	public static void main(String[] args) {
		String beginWord = "a";
		String endWord = "c";
		// String wordList[] = { "miss", "dusk", "kiss", "musk", "tusk", "diss",
		// "disk", "sang", "ties", "muss" };
		String wordList[] = { "a", "b", "c" };
		System.out.println(new YetAnotherWordLadder().wordLadder(beginWord, endWord, wordList));

	}

	public int wordLadder(String beginWord, String endWord, String[] wordList) {
		int shortestTrans = 0;
		List<String> wordsArrayList = new ArrayList<>(Arrays.asList(wordList));
		if (beginWord.length() != endWord.length() || !wordsArrayList.contains(endWord)) {
			return shortestTrans;
		}
		Queue<List<String>> transList = new LinkedList<>();
		Stack<List<List<String>>> levels = new Stack<>();
		Map<Integer, List<String>> mapOfDiffs = null;
		transList.add(new LinkedList<>(Arrays.asList(beginWord)));
		int levelId = 0;
		levels.add(new ArrayList(transList.peek()));
		while (!transList.isEmpty()) {
			List<String> wordsArrayListTemp = null;
			List<List<String>> level = new ArrayList<>();
			++levelId;
			System.out.printf("Level %d: \n", levelId);
			System.out.println("-------------------------");
			System.out.println(levels.lastElement());
			System.out.println("-------------------------");
			if (found)
				return levels.size();
			levels.add(level);
			for (List<String> strList : transList) {
				wordsArrayListTemp = new ArrayList<>(wordsArrayList);
				wordsArrayListTemp.removeAll(strList);
				String str = strList.get(strList.size() - 1);
				mapOfDiffs = getDiffsMap(str, wordsArrayListTemp);
				if (mapOfDiffs.get(1) != null && mapOfDiffs.get(1).size() > 0) {
					if (mapOfDiffs.get(1).contains(endWord)) {
						List<String> singleLevelElement = new ArrayList<>(strList);
						singleLevelElement.add(endWord);
						level.add(singleLevelElement);
						System.out.println("Found you!!");
						found = true;
						break;
					}
					for (String st : mapOfDiffs.get(1)) {
						List<String> singleLevelElement = new ArrayList<>(strList);
						singleLevelElement.add(st);
						level.add(singleLevelElement);
					}
				} else {
					break;
				}
			}
			transList.poll();
			transList.addAll(level);

		}

		return shortestTrans;

	}

	private Map<Integer, List<String>> getDiffsMap(String beginWord, List<String> wordList) {
		List<String> listOfStrings = null;
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

	public int numDiffBtwnStrings(String s1, String s2) {
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
