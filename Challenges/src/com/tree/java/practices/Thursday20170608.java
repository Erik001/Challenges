package com.tree.java.practices;

public class Thursday20170608 {

	public static void main(String[] args) {
		String d = "^-^+^-^+^<^>^<^>^-^+^^";
		int n = 5;
		System.out.println(new Thursday20170608().nextSquare(n));
		for(int i: new Thursday20170608().nav(d))
			System.out.println(i);
	}
	
	
	
	int[] nav(String d) {
		int[] result = new int[3];
		d.chars().forEach(c ->{
			switch (c)
			{
			case '+': result[2] += 1; 
			break;
			case '-': result[2] -= 1; 
			break;
			case '<': result[1] += 1; 
			break;
			case '>': result[1] -= 1; 
			break;
			case '^': result[0] += 1; 
			break;
			case 'v': result[0] -= 1; 
			break;
			}
		});
		return result;
	}

	int[] r = {0,0,0};
	int[] nav2(String d) {
	    for(int i: d.getBytes()) 
	        r[2-i/46] += (i%5&2)-1;
	    return r;
	}
	
	int[] Nav(String d) {
	    int r[] = new int[3];
	    for (int e:d.getBytes())
	        r[90/e] += 4*e/3%5-1;
	   
	    return r;
	}
	
	int nextSquare(int n) {
		System.out.println(n);
	    return (n += Math.sqrt(n) + 1 - n) * n;
	}
	
	int nextSquare2(int n) {
	    n /= Math.sqrt(n);
	    return ~n * ~n;
	}

}
