package com.tree.java.practices;

import java.util.ArrayList;
import java.util.List;

public class Tuesday06June2017Challenge {

	static List<Integer> binNum = new ArrayList<>();
	final static double TOP = Double.valueOf(Math.pow(2, 53));
	final static double FLOOR = Double.valueOf(Math.pow(2, 53));

	public static void main(String[] args) {
		long i = -7;
		// new Tuesday06June2017Challenge().weightYourTern(i);
		System.out.println(new Tuesday06June2017Challenge().weightYourTern5(i));
	}
	
	int s2, c;
	int weightYourTern5(long n) {
	    s2 = n < 0 ? -1 : 1;
	    
	    for(n *= s2; n > 0; n /= 3)
	        c += (++n % 3 - 1) * s2;
		
	    return c;
	}
	
	int r;
	int weightYourTern2(long n) {
	    for (; n != 0; n = n / 3 + n % 3 / 2)
	        r += (3 - ~n % 3) % 3 - 1;
	    return r;
	}
	
	
	int weightYourTern3(long n) {
	    for (; n!=0; n += n%3/2, n/=3)
	        r += n%3 - n%3/2*3;
	    return r;
	}
	
	long s,p;
	long weightYourTern4(long n) {
	    // -7 = -9 +3 -1
	    for(;n!=0;n/=3)
	        s+=(n+=p=n>0?1:-1)%3-p;
	    return s;
	}
	

	// int sum = 0;
	boolean isNegative = false;

	int weightYourTern(long n) {
		if (n < 0) {
			isNegative = true;
			return weightYourTern(-n);

		}

		if (n == 0)
			return 0;

		int r = mod3(n);
		if (r == 0)
			return weightYourTern(n / 3) + 0;
		if (r == 1)
			if (!isNegative)
				return weightYourTern(n / 3) + 1;
			else
				return weightYourTern(n / 3) - 1;
		if (r == 2)
			if (!isNegative)
				return weightYourTern((n + 1) / 3) + (-1);
			else
				return weightYourTern((n + 1) / 3) - (-1);
		return 0;

	}

	private int mod3(long v) {
		if (v > 0)
			return (int) (v % 3L);
		v = v % 3;
		return (int) ((v + 3) % 3L);
	}

}
