package com.tree.java.practices;

public class CountCliffs {
	int[][] M;
	int r;

	public static void main(String[] args) {
		// int seaMap[][] = { { 0, 1, 1 }, { 0, 1, 1 }, { 0, 0, 0 } };
		// int seaMap[][] = { { 1, 1, 1, 1 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 } };
		int seaMap[][] = { { 1, 1, 1, 1, 0 }, { 0, 1, 0, 0, 1 }, { 0, 0, 1, 0, 1 }, { 1, 1, 0, 0, 0 } };
		System.out.println(new CountCliffs().countCliffs(seaMap));

	}

	int countCliffs(int[][] S) {
		int x, y = 0;
		M = S;
		for (int[] e : S) {
			x = 0;
			for (int c : e) {
				r += c;
				g(x++, y);
			}
			y++;
		}
		return r;
	}

	void g(int x, int y) {
		if (x >= 0 && x < M[0].length && y >= 0 && y < M.length && M[y][x] > 0) {
			M[y][x] = 0;
			g(x + 1, y);
			g(x - 1, y);
			g(x, y + 1);
			g(x, y - 1);
		}
	}

}
