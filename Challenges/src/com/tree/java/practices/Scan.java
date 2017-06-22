package com.tree.java.practices;

public final class Scan
{
    public int offset;
    public String str;

    public Scan(String str)
    {
        this(str, 0);
    }

    public Scan(String str, int off)
    {
        this.str = str;
        this.offset = off;
    }

    public static int strtol(String s, int radix)
    {
        return new Scan(s).strtol(radix);
    }

    public int strtol(int radix)
    {
        int next, value;

        if (str.length() <= offset || radix < 2 || 36 < radix)
            return 0;

        int sign = 0;
        switch (str.charAt(offset)) {
        case '-': sign = -1; offset++; break;
        case '+': sign = +1; offset++; break;
        }

        for (value = 0, next = offset; next < str.length(); next++) {
            int digit = Character.digit(str.charAt(next), radix);
            if (digit < 0) break;
            value *= radix;
            value += digit;
        }

        if (offset < next)
            offset = next;
        else if (sign != 0)
            offset--;

        return sign == 0 ? value : value * sign;
    }
}