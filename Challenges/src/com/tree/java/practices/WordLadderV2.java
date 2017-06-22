package com.tree.java.practices;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadderV2 extends YetAnotherWordLadder {

	public static void main(String[] args) {
		String beginWord = "teach";
		String endWord = "place";
		// String wordList[] = { "miss", "dusk", "kiss", "musk", "tusk", "diss",
		// "disk", "sang", "ties", "muss" };
		String wordList[] = { "peale", "wilts", "place", "fetch", "purer", "pooch", "peace", "poach", "berra", "teach",
				"rheum", "peach" };

		System.out.println(new WordLadderV2().wordLadderImproved(beginWord, endWord, wordList));
		// found = false;
		// System.out.println(new
		// YetAnotherWordLadder().wordLadderImproved(beginWord, endWord,
		// wordList));
	}

	private int shortestTrans = 0;

	public int wordLadderImproved(String beginWord, String endWord, String[] wordList) {

		List<String> wordArrayList = new ArrayList<>(Arrays.asList(wordList));
		if (beginWord.length() != endWord.length() || !wordArrayList.contains(endWord)) {
			return shortestTrans ;
		}
		Queue<Deque<String>> transList = new LinkedList<>();
		List<String> diffsList = null;
		transList.add(new ArrayDeque<String>(Arrays.asList(beginWord)));
		while (!transList.isEmpty()) {
			System.out.println(transList);
			Deque<String> queueHead = transList.peek();
			Queue<Deque<String>> level = new LinkedList<>();
			for (Deque<String> ad : transList) {
				List<String> wordArrayListTmp = wordArrayList;
				wordArrayListTmp.removeAll(ad);
				if(!found)
					level.addAll(getOneDiffOnlyListV2(ad, wordArrayListTmp, endWord, shortestTrans));
				else
					return shortestTrans;
			}
			transList.addAll(level);
			transList.poll();
		}
		return shortestTrans;
	}

	public Queue<Deque<String>> getOneDiffOnlyListV2(Deque<String> ad, List<String> wordList, String endWord, Integer shortestTrans) {
		Queue<Deque<String>> tmpStack = new ArrayDeque<>();
		String beginWord = ad.peek();
		for (String tmpWord : wordList) {
			int diffs = numDiffBtwnStrings(beginWord, tmpWord);
			if (diffs == 1) {
				Deque<String> arry = new ArrayDeque<>(ad);
				arry.addFirst(tmpWord);
				tmpStack.add(arry);
				if (tmpWord.equals(endWord)) {
					found = true;
					this.shortestTrans = arry.size();
					break;
				}
				
			}
		}
		return tmpStack;
	}

}
