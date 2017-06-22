package com.tree.java.practices.best;

import java.util.ArrayDeque;
import java.util.Queue;

public class FirstWordLader {

	public static void main(String[] args) {
		String beginWord = "teach";
		String endWord = "place";
		// String wordList[] = { "miss", "dusk", "kiss", "musk", "tusk", "diss",
		// "disk", "sang", "ties", "muss" };
		String wordList[] = { "peale", "wilts", "place", "fetch", "purer", "pooch", "peace", "poach", "berra", "teach",
				"rheum", "peach" };
		System.out.println(new FirstWordLader().wordLadder(beginWord, endWord, wordList));
	}

	int wordLadder(String beginWord, String endWord, String[] wordList) {
		String[] wordListWithBegin = new String[wordList.length + 1];
		wordListWithBegin[0] = beginWord;
		System.arraycopy(wordList, 0, wordListWithBegin, 1, wordList.length);
		wordList = wordListWithBegin;

		int endIndex = -1;
		for (int i = 0; i < wordList.length; ++i)
			if (endWord.equals(wordList[i]))
				endIndex = i;
		if (endIndex == -1)
			return 0;

		int[] distance = new int[wordList.length];

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(0);
		while (!queue.isEmpty()) {
			int current = queue.remove();

			for (int i = 1; i < wordList.length; ++i) {
				if (distance[i] == 0 && diff(wordList[current], wordList[i]) == 1) {
					distance[i] = distance[current] + 1;
					queue.add(i);
				}
			}
		}

		return distance[endIndex] == 0 ? 0 : distance[endIndex] + 1;
	}

	int diff(String s1, String s2) {
		int count = 0;
		for (int i = 0; i < s1.length(); ++i) {
			if (s1.charAt(i) != s2.charAt(i))
				++count;
		}
		return count;
	}

}
