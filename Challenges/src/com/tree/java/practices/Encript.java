package com.tree.java.practices;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Encript {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new Encript().decrypt("þÁ|óþìJþ^J´^ hþ|ñ–“‚ÛŠÅÑh"));
	}

	public String decrypt(String sStr) throws UnsupportedEncodingException {

		StringBuilder sAux;
		int iPos = 0;
		int iNum = 0;
		char[] iAux = new char[5];
		char iByte = (char) (254);
		boolean bOk = false;

		if ((sStr == null || sStr.isEmpty()) && sStr.toCharArray()[0] != iByte)
			return "";
		// if (!bHexToAscii(sStr)) goto EndX;
		sStr = hexToAscii(sStr);
		// if (sStr[0] != iByte) goto EndX;
		if ((sStr = bMix(sStr)).equals(""))
			return "";
		if ((sStr = bMix(sStr)).equals(""))
			return "";
		for (iNum = 0; iNum < 5; iNum++) {
			iAux[iNum] = sStr.charAt(iNum);
		}
		sAux = new StringBuilder(sStr.substring(sStr.length() - 20, sStr.length()));

		byte[] bytesAux = sAux.toString().getBytes("US-ASCII");
		char[] charsAux = new char[sAux.length()];

		for (iPos = 0; iPos < 20; iPos++) {
			if (sAux.charAt(iPos) != iAux[iPos % 5] && (sAux.charAt(iPos) ^ iAux[iPos % 5]) != 34) {
				sAux.replace(iPos, iPos + 1, (char) (sAux.charAt(iPos) ^ iAux[iPos % 5]) + "");
				//charsAux[iPos] = (char) (sAux.charAt(iPos) ^ iAux[iPos % 5]);
				
			}
		}
		iPos = sAux.lastIndexOf(iByte + "");
		if (iPos >= 0)
			sStr = sAux.substring(0, iPos);
		else
			sStr = sAux.toString();
		bOk = true;
		return sStr;
	}

	public String bMix(String sStr) {
		// Mix the contents in the string
		boolean bOk = false;
		int iRow = 0;

		while (sStr.length() < 25)
			sStr = sStr + " ";
		if (sStr.length() != 25)
			return "";
		StringBuilder sAux = new StringBuilder(sStr);

		for (iRow = 0; iRow < 12; iRow++) {
			sAux.replace(iRow * 2 + 1, iRow * 2 + 1 + 1, sStr.toCharArray()[((iRow * 2 + 8) % 24) + 1] + "");
			sAux.replace(iRow * 2 + 2, iRow * 2 + 2 + 1, sStr.toCharArray()[((iRow * 2 + 17) % 24) + 1] + "");
		}
		// sStr = sAux;
		bOk = true;

		return sAux.toString();
	}

	String hexToAscii(String sStr) {
		// Convierte la cadena dada de ascii a hexascii
		String sOut = "";
		String sAux = "";
		int iRow = 0;
		int iRows = 0;
		int iAux = 0;
		// char pFin = 0;

		if (sStr.length() != 50 || Character.toUpperCase(sStr.toCharArray()[0]) != 'F'
				|| Character.toUpperCase(sStr.toCharArray()[1]) != 'E' || sStr.length() % 2 == 0)
			return sStr;

		iRows = sStr.length() / 2;

		for (iRow = 0; iRow < iRows; iRow++) {
			// iAux = Long.parseLong(sStr.substring(iRow * 2, 2), 16);
			iAux = Scan.strtol(sStr.substring(iRow * 2, (iRow * 2) + 1), 16);
			// strtol(sStr.substring(iRow * 2, 2), pFin, 16);
			sAux = sAux.format("%c", iAux);
			sOut += sAux;
		}
		return sOut;

	}

}
