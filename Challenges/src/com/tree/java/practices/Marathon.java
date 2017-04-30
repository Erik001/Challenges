package com.tree.java.practices;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.TreeSet;

public class Marathon {
	int calculationsWithCoins(int a, int b, int c) {
		if (a == b && b == c)
			return 3;
		else if (a != b && b != c && a != c) {
			if ((a + b == c || a + c == b || b + c == a)) {
				return 6;
			} else {
				return 7;
			}
		} else {
			if ((a + b == c || a + c == b || b + c == a)) {
				return 4;
			} else {
				return 5;
			}
		}

	}

	int calculationsWithCoins2(int a, int b, int c) {
		int r = 7;
		int[] v = { a, b, c, a + b, b + c, c + a, a + b + c };

		for (int i = 0; i < v.length; i++) {
			for (int j = i + 1; j < v.length; j++) {
				if (v[j] == v[i]) {
					r--;
					break;
				}
			}
		}
		return r;
	}

	int calculationsWithCoins_best(int a, int b, int c) {
		HashSet<Integer> set = new HashSet();

		set.add(a);
		set.add(b);
		set.add(c);
		set.add(a + b);
		set.add(a + c);
		set.add(b + c);
		set.add(b + c + a);

		return set.size();
	}

	int calculationsWithCoins3(int a, int b, int c) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < 8; i++) {
			int tmp = 0;
			if ((i & 1) != 0)
				tmp += a;
			if ((i & 2) != 0)
				tmp += b;
			if ((i & 4) != 0)
				tmp += c;
			set.add(tmp);
		}
		return set.size() - 1;
	}

	int calculationsWithCoins4(int a, int b, int c) {
		boolean is[] = new boolean[31];

		is[a] = true;
		is[b] = true;
		is[c] = true;
		is[a + b] = true;
		is[a + c] = true;
		is[b + c] = true;
		is[a + b + c] = true;

		int cnt = 0;
		for (int i = 0; i < 31; i++)
			if (is[i])
				cnt++;

		return cnt;
	}

	int exerciseElaboration(int p, int n) {
		BigInteger wholeNumber = new BigInteger("0");
		BigInteger result = new BigInteger("0");
		;
		for (int a = 0; a < n + 2; a++) {
			if (a == 0 || a == n + 1) {
				wholeNumber = wholeNumber.add(new BigInteger("" + p));
			}
			if (a < n + 1)
				wholeNumber = wholeNumber.multiply(new BigInteger("" + 10));
		}
		BigInteger numberToEvaluate = wholeNumber.multiply(wholeNumber);
		if (!(numberToEvaluate.compareTo(new BigInteger("" + 0)) == 0))
			while (numberToEvaluate.compareTo(new BigInteger("" + 0)) == 1) {
				result = result.add(numberToEvaluate.mod(new BigInteger("" + 10)));
				numberToEvaluate = numberToEvaluate.divide(new BigInteger("" + 10));
			}
		return result.intValue();

	}
	
	int exerciseElaboration2(int p, int n) {
	    StringBuilder sb = new StringBuilder();
	    sb.append(p);
	    for (int a = 0; a < n; a++)
	        sb.append('0');
	    sb.append(p);
	    
	    java.math.BigInteger b = new java.math.BigInteger(sb.toString());
	    
	    String s = b.multiply(b).toString();
	    
	    int res = 0;
	    for (char c : s.toCharArray())
	        res += c - '0';
	    
	    return res;
	}
	
	int exerciseElaboration3(int p, int n) {
		int numbPow = 0;
		int calculatedNumber = 0;
		int result = 0;
		int middleNumb = 0;
	    if(n == 0){
	    	numbPow = p*10 + p;
			numbPow *= numbPow;
	    	while(numbPow > 0){
	    		result += numbPow % 10;
	    		numbPow /= 10;
	    	}
	    }else{
	    	numbPow = p * p;
	    	if((numbPow + numbPow) % 10 == 0){
	    		middleNumb = (numbPow + numbPow) / 10;
	    		calculatedNumber = numbPow * 1000 + middleNumb * 100 + numbPow;
	    	}else{
	    		middleNumb = numbPow + numbPow;
	    		calculatedNumber = numbPow * 100000 + middleNumb * 100 + numbPow;
	    	}
	    }
	    
	    while (calculatedNumber> 0) {
			result += calculatedNumber% 10;
			calculatedNumber /= 10;
		}
	    
	    return result;
	}


	public static void main(String... args) {
		Marathon mt = new Marathon();
		// System.out.println(new Marathon().calculationsWithCoins3(5, 5, 5));

		System.out.println(mt.exerciseElaboration3(6, 2));
	}

}
