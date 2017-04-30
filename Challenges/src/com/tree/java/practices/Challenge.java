package com.tree.java.practices;

import java.util.Calendar;

public class Challenge {

	public static void main2(String[] args) {
		String[] names = { "John", "Martin" };
		// statuses: {false, false}
		// projects: {2, 1}
		// tasks: {16, 5}
		AsanaProject ap = new AsanaProject();
		/*
		 * System.out.println(ap.smartAssigning(new String[] { "John", "Martin"
		 * }, new boolean[] { false, true }, new int[] { 2, 1 }, new int[] { 6,
		 * 5 }));
		 */
		Calendar cal = Calendar.getInstance();
		/*
		 * String firstDate = "01/01/2015"; int k = 2; String[] daysOfTheWeek =
		 * { "Monday", "Thursday" }; int n = 4;
		 */
		String firstDate = "23/02/2000";
		int k = 2;
		String[] daysOfTheWeek = { "Wednesday", "Friday" };
		int n = 4;

		String[] output = ap.recurringTaskAltern2(firstDate, k, daysOfTheWeek, n);
		for (int a = 0; a < output.length; a++) {
			System.out.println(output[a]);
		}

	}

	public static void main(String[] args) {
		double departure[] = { 0, 0.2 };
		double destination[] = { 7, 0.5 };
		int[] carDimensions = { 3, 2 };
		int parkingLot2[][] = { { 1, 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 1 } };
		int parkingLot3[][] = { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		int parkingLot4[][] = { { 1, 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 1 } };
		int parkingLot[][] = { { 1, 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1, 1 } };
		int luckySpot[] = { 1, 1, 2, 3 };
		UberProject up = new UberProject();
		System.out.println(up.parkingSpot(carDimensions, parkingLot, luckySpot));
		// System.out.println(up.perfectCity(departure, destination));

	}
}
