package com.tree.java.practices;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class YetAnotherWordLadder {
	public static boolean found;

	public static void main(String[] args) {
		String beginWord = "teach";
		String endWord = "place";
		// String wordList[] = { "miss", "dusk", "kiss", "musk", "tusk", "diss",
		// "disk", "sang", "ties", "muss" };
		String wordList[] = { "peale", "wilts", "place", "fetch", "purer", "pooch", "peace", "poach", "berra", "teach",
				"rheum", "peach" };

		System.out.println(new YetAnotherWordLadder().wordLadderImproved(beginWord, endWord, wordList));
		//found = false;
		//System.out.println(new YetAnotherWordLadder().wordLadderImproved(beginWord, endWord, wordList));
	}

	public int wordLadder(String beginWord, String endWord, String[] wordList) {
		int shortestTrans = 0;
		List<String> wordsArrayList = new ArrayList<>(Arrays.asList(wordList));
		if (beginWord.length() != endWord.length() || !wordsArrayList.contains(endWord)) {
			return shortestTrans;
		}
		Queue<Deque<String>> transList = new LinkedList<>();
		Stack<List<Deque<String>>> levels = new Stack<>();
		Map<Integer, List<String>> mapOfDiffs = null;
		transList.add(new ArrayDeque<String>(Arrays.asList(beginWord)));
		int levelId = 0;
		levels.add(new ArrayList(transList.peek()));
		while (!transList.isEmpty()) {
			List<String> wordsArrayListTemp = null;
			List<Deque<String>> level = new ArrayList<>();
			++levelId;
			System.out.printf("Level %d: \n", levelId);
			System.out.println("-------------------------");
			System.out.println(levels.lastElement());
			System.out.println("-------------------------");
			if (found)
				return levels.size();
			levels.add(level);
			for (Deque<String> strList : transList) {
				wordsArrayListTemp = new ArrayList<>(wordsArrayList);
				wordsArrayListTemp.removeAll(strList);
				String str = strList.getLast();
				mapOfDiffs = getDiffsMap(str, wordsArrayListTemp);

				if (mapOfDiffs.get(1) != null && mapOfDiffs.get(1).size() > 0) {

					if (mapOfDiffs.get(1).contains(endWord)) {
						Deque<String> singleLevelElement = new ArrayDeque<>(strList);
						singleLevelElement.add(endWord);
						level.add(singleLevelElement);
						System.out.println("Found you!!");
						found = true;
						break;
					}
					for (String st : mapOfDiffs.get(1)) {
						Deque<String> singleLevelElement = new ArrayDeque<>(strList);
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

	public int wordLadderImproved(String beginWord, String endWord, String[] wordList) {
		int shortestTrans = 0;
		List<String> wordsArrayList = new ArrayList<>(Arrays.asList(wordList));
		if (beginWord.length() != endWord.length() || !wordsArrayList.contains(endWord)) {
			return shortestTrans;
		}
		Queue<Deque<String>> transList = new LinkedList<>();
		Stack<List<Deque<String>>> levels = new Stack<>();
		List<String> diffsList = null;
		transList.add(new ArrayDeque<String>(Arrays.asList(beginWord)));
		int levelId = 0;
		levels.add(new ArrayList(transList.peek()));
		while (!transList.isEmpty()) {
			List<String> wordsArrayListTemp = null;
			List<Deque<String>> level = new ArrayList<>();
			++levelId;
			System.out.printf("Level %d: \n", levelId);
			System.out.println("-------------------------");
			System.out.println(levels.lastElement());
			System.out.println("-------------------------");
			if (found)
				return levels.size();
			levels.add(level);
			for (Deque<String> strList : transList) {
				wordsArrayListTemp = new ArrayList<>(wordsArrayList);
				wordsArrayListTemp.removeAll(strList);
				String str = strList.getLast();
				diffsList = getOneDiffOnlyList(str, wordsArrayListTemp);

				if (diffsList != null && diffsList.size() > 0) {

					if (diffsList.contains(endWord)) {
						Deque<String> singleLevelElement = new ArrayDeque<>(strList);
						singleLevelElement.add(endWord);
						level.add(singleLevelElement);
						System.out.println("Found you!!");
						found = true;
						break;
					}
					for (String st : diffsList) {
						Deque<String> singleLevelElement = new ArrayDeque<>(strList);
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
		// Collections.sort(wordList);
		for (String tmpWord : wordList) {
			int diffs = numDiffBtwnStrings(beginWord, tmpWord);
			if (!mapOfDiffs.containsKey(diffs)) {
				listOfStrings = new ArrayList<>(Arrays.asList(tmpWord));
				// listOfStrings.add(tmpWord);
				mapOfDiffs.put(diffs, listOfStrings);
			} else {
				mapOfDiffs.get(diffs).add(tmpWord);
			}
		}
		return mapOfDiffs;
	}

	public List<String> getOneDiffOnlyList(String beginWord, List<String> wordList) {
		List<String> listOfStrings = new ArrayList<>();
		Collections.sort(wordList);
		for (String tmpWord : wordList) {
			int diffs = numDiffBtwnStrings(beginWord, tmpWord);
			if (diffs == 1) {
				listOfStrings.add(tmpWord);
			}
		}
		return listOfStrings;
	}

	public int numDiffBtwnStrings(String s1, String s2) {
		int result = 0;
		/*
		 * char[] s1Array = s1.toCharArray(); char[] s2Array = s2.toCharArray();
		 * if (Arrays.equals(s1Array, s2Array)) return 0; else { for (int i = 0;
		 * i < s1Array.length; i++) { if (s1Array[i] != s2Array[i]) { result++;
		 * } } }
		 */

		int i = 0;
		for (char c : s1.toCharArray()) {
			if (i < s2.length() && s2.charAt(i++) != c)
				result++;
		}

		return result;
	}

}
