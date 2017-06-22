package com.tree.java.practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RelativePrimes {

	public static void main(String[] args) {
		int[] values = { 143, 21, 2, 5, 14 };
		int z = 1 << 22;
		System.out.println(Integer.MAX_VALUE);
		System.out.println(z);
		for (Object i : new RelativePrimes().relativePrimes(values)) {
			System.out.println(i);
		}
	}

	int[] relativePrimes(int[] v) {
		int d = 1010, c = 0, q = 0, i = 0, z = 1 << 22;
		for (; --d > 1; c = q = i = 0)
			for (int e : v) {
				if (e % z % d < 1) {
					v[q] |= c;
					v[q = i] |= c;
					c = z;
				}
				i++;
			}
		return Arrays.stream(v).filter(e -> e < z).toArray();
		
	}

	int[] relativePrimes2(int[] v) {
		int i2 = 0, l = 0, M = 1010, c2[] = new int[M];
		for (int x : v)
			for (i2 = 1; ++i2 < M;)
				if (x % i2 < 1)
					c2[i2]++;
		for (int x : v) {
			for (i2 = 1; ++i2 < M; i2 = x % i2 < 1 & c2[i2] > 1 ? M : i2)
				;
			if (i2 <= M)
				v[l++] = x;
		}
		return Arrays.copyOf(v, l);
	}

	List relativePrimes3(int[] V) {

		int n = 1011, C[] = new int[n], i, j;
		for (int x : V)
			for (i = 1; ++i < n;)
				C[i] += 1 > x % i ? 1 : 0;

		List R = new ArrayList();
		for (int x : V) {
			for (i = j = 1; ++i < n;)
				j = x % i < 1 & C[i] > 1 ? 0 : j;

			if (j > 0)
				R.add(x);
		}

		return R;
	}

	List relativePrimes4(int[] v) {
		int i = 0, r = 0;
		List l = new ArrayList();
		int t = 1010, p[] = new int[1011];
		for (int w : v)
			for (i = 2, p[w > t ? 0 : w]++; i * i <= w; i++)
				if (w % i < 1) {
					p[i > t ? 0 : i]++;
					if (i * i < w)
						p[w / i > t ? 0 : w / i]++;
				}

		for (int w : v) {
			for (p[0] = r = 0, i = 2; i * i <= w; i++)
				if (w % i < 1 && (p[i > t ? 0 : i] > 1 || p[w / i > t ? 0 : w / i] > 1))
					r = 1;

			if (p[w > t ? 0 : w] > 1)
				r = 1;
			if (r < 1)
				l.add(w);
		}
		return l;
	}

	int i, j, L, pL, vL;

	int[] pn(int n) {
		int[] t = new int[n + 1];
		for (i = 2, L = 0; i <= n; i++) {
			int c = 0;
			for (j = 2; j < i; j++)
				if (i % j == 0)
					c++;

			if (c == 0)
				t[L++] = i;
		}
		return Arrays.copyOf(t, L);
	}

	int[] relativePrimes5(int[] v) {
		int[] p = pn(1010);
		int[] c = new int[pL = p.length], r = new int[vL = v.length];
		for (i = 0; i < vL; ++i)
			for (j = 0; j < pL; ++j)
				if (v[i] % p[j] == 0)
					c[j]++;

		for (i = L = 0; i < vL; ++i) {
			for (j = 0; j < pL; ++j)
				if (c[j] > 1 && v[i] % p[j] == 0)
					r[i] = 1;

			if (r[i] == 0)
				r[L++] = v[i];
		}
		return Arrays.copyOf(r, L);
	}
	
	Object[] relativePrimes6(int[] v) {
	    List<Integer> s = new ArrayList<Integer>();
	    List<Integer> r = new ArrayList<Integer>();
	    s.add(2);
	    for (int i = 3; i < 1010; i++){
	        boolean f = true;
	        for (int j : s)
	            if (i % j == 0){
	                f = false;
	                break;
	            }
	        if (f)
	            s.add(i);
	    }
	    int c = v.length;
	    int[] p = new int[s.size()];
	    Arrays.fill(p,0);
	    for (int i:v)
	        for (int j = 0; j < s.size(); j++)
	            while (i % s.get(j) == 0){
	                p[j]++;
	                i/=s.get(j);
	            }
	    
	    for (int i:v){
	        boolean f = true;
	        r.add(i);
	        for (int j = 0; j < s.size(); j++){
	            if (i % s.get(j) != 0)
	                continue;
	            int h = 0;
	            while (i % s.get(j) == 0){
	                h++;
	                i/=s.get(j);
	            }
	            if (h < p[j]){
	                f = false;
	                break;
	            }
	        }
	        if (!f)
	            r.remove(r.size()-1);
	    }
	    return r.toArray();
	}

}
