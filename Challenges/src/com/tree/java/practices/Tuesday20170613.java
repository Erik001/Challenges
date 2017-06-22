package com.tree.java.practices;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class Tuesday20170613 {

	public static void main(String[] args) {
		String vocabulary[]  = { "globe", "job", "excellent", "round", "joy", "lock", "vial", "knot", "wait", "waist",
				"great", "abuse", "academic", "accept", "access", "zone", "account", "accuse", "act", "turn", "come",
				"move", "leader", "query", "school", "part", "letter", "roll", "yield", "next", "free", "froze", "pair",
				"arrow", "feel", "float", "mean", "flash" };
		
		System.out.println(new Tuesday20170613().alphabetExercise2(vocabulary));
		

	}

	int alphabetExercise(String[] v) {
		List<E> x = new ArrayList<>();
		int n = v.length, r = n + 26;
		for (int i = 0; i < n; i++) {
			x.add(new E(r, i, v[i].length()));
			for (char c : v[i].toCharArray()) {
				x.add(new E(i, n + c - 97, 0));
			}
		}
		for (int i = 0; i < 26; i++) {
			x.add(new E(n + i, r + 1, 0));
		}
		return Q(W(r + 2, x), r, r + 1);
	}

	class E {
		int f, t, s, o;
		E m;

		public E(int g, int u, int v) {
			f = g;
			t = u;
			s = v;
		}
	}

	E[][] W(int n, List<E> h) {
		E[][] g = new E[n][];
		for (int i = h.size() - 1; i >= 0; i--) {
			E o = h.get(i), c = new E(o.t, o.f, -o.s);
			c.o = 1;
			c.m = o;
			o.m = c;
			h.add(c);
		}

		int[] p = new int[n];
		for (E e : h)
			p[e.f]++;
		for (int i = 0; i < n; i++)
			g[i] = new E[p[i]];
		for (E e : h)
			g[e.f][--p[e.f]] = e;
		return g;
	}

	int Q(E[][] g, int y, int K) {
		int n = g.length, T = 0;

		int[] d = new int[n], I = new int[n];
		Queue<Integer> q = new ArrayDeque<>();
		while (true) {
			E[] z = new E[n];
			Arrays.fill(d, 1 << 29);
			d[y] = 0;
			q.add(y);
			while (!q.isEmpty()) {
				int c = q.poll();
				I[c] = 0;
				for (E h : g[c]) {
					if (1 > h.o) {
						int w = d[c] + h.s;
						if (d[h.t] > w) {
							z[h.t] = h;
							d[h.t] = w;
							if (I[h.t] == 0) {
								q.add(h.t);
								I[h.t] = 1;
							}
						}
					}
				}
			}

			if (z[K] == null)
				break;

			long O = 0;
			for (E e = z[K]; e != null; e = z[e.f]) {
				O += e.s;
			}
			T += O;
			for (E e = z[K]; e != null; e.o++, e.m.o--, e = z[e.f])
				;

		}
		return T;
	}
	
	
	// https://gist.github.com/pathikrit/bc09b720f476c79a3553#file-library-java-L1396
	/*
	 * w[i][j] = amount bidder j is willing to pay for item i (0 if he is not bidding)
	 * run time is O(nm^2) where n = #of items and m = #of bidders
	 * resets negative bids in w to 0
	 * returns a, where a[i] = j means ith item got assigned to bidder j
	 * a[i] = -1 means item i did not get assigned
	 * for minimizing set w[i][j] = max(w) - w[i][j]
	 * for assigning all, w[i][j] = min(w) + w[i][j]
	 */
	int[] hungarianMethod(int w[][]) {
	    final int n = w.length, m = w[0].length, PHI = -1, NOL = -2,
	        INF = 1<<30;
	    boolean[] x[] = new boolean[n][m], ss = new boolean[n], st = new boolean[m];
	    int[] u = new int[n], v = new int[m], p = new int[m], ls = new int[n], lt = new int[m], a = new int[n];
	    int f = 0;

	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < m; j++)
	            f = Math.max(f, w[i][j]);

	    Arrays.fill(u, f);
	    Arrays.fill(p, INF);
	    Arrays.fill(lt, NOL);
	    Arrays.fill(ls, PHI);
	    Arrays.fill(a, -1);

	    while (true) {
	        f = -1;
	        for (int i = 0; i < n && f == -1; i++)
	            if (ls[i] != NOL && !ss[i])
	                f = i;

	        if (f != -1) {
	            ss[f] = true;
	            for (int j = 0; j < m; j++)
	                if (!x[f][j] && u[f] + v[j] - w[f][j] < p[j]) {
	                    lt[j] = f;
	                    p[j] = u[f] + v[j] - w[f][j];
	                }
	        } else {
	            for (int i = 0; i < m && f == -1; i++)
	                if (lt[i] != NOL && !st[i] && p[i] == 0)
	                    f = i;

	            if (f == -1) {
	                int d1 = INF, d2 = INF, d;
	                for (int i : u)
	                    d1 = Math.min(d1, i);

	                for (int i : p)
	                    if (i > 0)
	                        d2 = Math.min(d2, i);

	                d = Math.min(d1, d2);

	                for (int i = 0; i < n; i++)
	                    if (ls[i] != NOL)
	                        u[i] -= d;

	                for (int i = 0; i < m; i++) {
	                    if (p[i] == 0)
	                        v[i] += d;
	                    if (p[i] > 0 && lt[i] != NOL)
	                        p[i] -= d;
	                }

	                if (d2 >= d1)
	                    break;
	            } else {
	                st[f] = true;
	                int s = -1;

	                for (int i = 0; i < n && s == -1; i++)
	                    if (x[i][f])
	                        s = i;

	                if (s == -1) {
	                    for (int l, r; ; f = r) {
	                        r = f;
	                        l = lt[r];

	                        if (r >= 0 && l >= 0)
	                            x[l][r] = !x[l][r];
	                        else
	                            break;

	                        r = ls[l];
	                        if (r >= 0 && l >= 0)
	                            x[l][r] = !x[l][r];
	                        else
	                            break;
	                    }

	                    Arrays.fill(p, INF);
	                    Arrays.fill(lt, NOL);
	                    Arrays.fill(ls, NOL);
	                    Arrays.fill(ss, false);
	                    Arrays.fill(st, false);

	                    for (int i = 0; i < n; i++) {
	                        boolean ex = true;
	                        for (int j = 0; j < m && ex; j++)
	                            ex = !x[i][j];
	                        if (ex)
	                            ls[i] = PHI;
	                    }
	                } else
	                    ls[s] = f;
	            }
	        }
	    }

	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < m; j++)
	            if (x[i][j])
	                a[j] = i;
	    return a;
	}



	int alphabetExercise2(String[] vocabulary){
	    Arrays.sort(vocabulary, new Comparator<String>() {
	        public int compare(String s, String t) {
	            return s.length() - t.length();
	        }
	    });
	    int n = vocabulary.length;
//	    System.out.println("n="+n);
//	    System.out.println(Arrays.toString(vocabulary));
	    
	    boolean selected[] = new boolean[n];
	    for (char c = 'a'; c <= 'z'; c++){
	        int count = 0;
	        for (int a = 0; a < n && count < 26; a++){
	            if (vocabulary[a].indexOf(c) >= 0){
	                selected[a] = true;
	                count++;
	            }
	        }
	    }
	    
	    int selectedCount = 0;
	    for (boolean e:selected)
	        if (e)
	            selectedCount++;
	    String[] vo = new String[selectedCount];
	    for (int r = 0, w = 0; r < n; r++){
	        if (selected[r]){
	            vo[w] = vocabulary[r];
	            w++;
	        }
	    }

	    n = selectedCount;
	    vocabulary = vo;
	    int[][] mat = new int[n][26];
	    for (int c = 0; c < n; c++){
	        String word = vocabulary[c];
	        for (int r = 0; r < 26; r++){
	            if (word.indexOf(r+'a') >= 0)
	                mat[c][r] = 100 - word.length();
	        }
	    }
	    
	    int[] a = hungarianMethod(mat);
//	    System.out.println(Arrays.toString(a));
//	    System.out.println(a.length);
	    int res = 0;
	    for (int b = 0; b < 26; b++){
//	        System.out.println(vocabulary[a[b]]);
	        res += vocabulary[a[b]].length();
	    }
	    return res;
	}



}
