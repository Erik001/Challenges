package com.tree.java.practices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AsanaProject {
	public int[] tasksTypes(int[] deadlines, int day) {
		int[] listResults = new int[3];
		for (int i = 0; i < deadlines.length; i++) {
			if (deadlines[i] <= day) {
				listResults[0] += 1;
			} else if (deadlines[i] >= (day + 1) && deadlines[i] <= (day + 7)) {
				listResults[1] += 1;
			} else {
				listResults[2] += 1;
			}
		}
		return listResults;
	}

	public String smartAssigning(String[] names, boolean[] statuses, int[] projects, int[] tasks) {

		if (names.length != statuses.length && names.length != projects.length && names.length != tasks.length) {
			return null;
		}

		int availabilities[] = new int[names.length];

		int availabilityIndicator = 0;

		for (int i = 0; i < names.length; i++) {
			if (statuses[i] != true) {
				availabilities[i] = 1;
				for (int j = 0; j < statuses.length; j++) {
					if (statuses[j] != true && i != j) {
						if (tasks[i] > tasks[j] || (tasks[i] == tasks[j]) && projects[i] > projects[j]) {
							availabilities[i] = 0;
							availabilities[j] = 1;
						}
					}
				}
			}
		}

		String name = "";

		for (int a = 0; a < availabilities.length; a++) {
			if (availabilities[a] == 1) {
				name = names[a];
			}
		}
		return name;
	}

	public int[] monthsOfTheYear = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public List<String> daysOfTheWeekList = new ArrayList(
			Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

	public String[] recurringTask(String firstDate, int k, String[] daysOfTheWeek, int n) {

		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		List<String> arrayOfDates = new ArrayList<>();
		try {
			Date currentDate = sd.parse(firstDate);
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			int[] daysOfWeekNumeric = new int[daysOfTheWeek.length];
			for (int i = 0; i < daysOfTheWeek.length; i++) {
				daysOfWeekNumeric[i] = daysOfTheWeekList.indexOf(daysOfTheWeek[i]) + 1;
			}
			int usedDates = 0;
			int difference = 0;
			int n2 = n;
			while (n2 > 0) {

				for (int j = 0; j < daysOfWeekNumeric.length; j++) {
					if (c.get(Calendar.DAY_OF_WEEK) <= daysOfWeekNumeric[j] && arrayOfDates.size() < n) {
						difference = daysOfWeekNumeric[j] - c.get(Calendar.DAY_OF_WEEK);
						c.add(Calendar.DATE, difference);
						arrayOfDates.add(sd.format(c.getTime()));
						c.add(Calendar.DATE, -difference);
						--n2;
						++usedDates;
					} else if (usedDates > 0 && c.get(Calendar.DAY_OF_WEEK) > daysOfWeekNumeric[j]
							&& arrayOfDates.size() < n) {
						difference = daysOfWeekNumeric[j] - c.get(Calendar.DAY_OF_WEEK);
						c.add(Calendar.DATE, difference);
						arrayOfDates.add(sd.format(c.getTime()));
						--n2;
						++usedDates;
						if (usedDates == daysOfTheWeek.length) {
							c.add(Calendar.WEEK_OF_YEAR, 1);
							usedDates = 0;
						}
					}
				}
				if (usedDates < daysOfTheWeek.length) {
					c.add(Calendar.WEEK_OF_YEAR, 1);
				} else {
					usedDates = 0;
					c.add(Calendar.WEEK_OF_YEAR, 1);
				}

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return arrayOfDates.toArray(new String[arrayOfDates.size()]);
	}

	public String[] recurringTaskImproved(String firstDate, int k, String[] daysOfTheWeek, int n) {
		Map<String, Integer> daysOfTheWeekMap = new HashMap<>();
		daysOfTheWeekMap.put("Sunday", 1);
		daysOfTheWeekMap.put("Monday", 2);
		daysOfTheWeekMap.put("Tuesday", 3);
		daysOfTheWeekMap.put("Wednesday", 4);
		daysOfTheWeekMap.put("Thursday", 5);
		daysOfTheWeekMap.put("Friday", 6);
		daysOfTheWeekMap.put("Saturday", 7);

		List<String> arrayOfDates = new LinkedList<>();

		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate;
		Calendar c = Calendar.getInstance();
		try {
			currentDate = sd.parse(firstDate);
			c.setTime(currentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean hasFirstWeekPassed = false;
		int traversedDaysCounter = 0;
		for (int l = 0; l < n;) {

			for (String day : daysOfTheWeek) {
				traversedDaysCounter++;
				int numericDay = daysOfTheWeekMap.get(day);
				int difference = 0;
				if (numericDay == c.get(Calendar.DAY_OF_WEEK) || numericDay > c.get(Calendar.DAY_OF_WEEK) && l < n) {
					difference = numericDay - c.get(Calendar.DAY_OF_WEEK);
					c.add(Calendar.DATE, difference);
					arrayOfDates.add(sd.format(c.getTime()));
					l++;

				} else if (numericDay < c.get(Calendar.DAY_OF_WEEK) && traversedDaysCounter > daysOfTheWeek.length
						&& l < n) {
					difference = numericDay - c.get(Calendar.DAY_OF_WEEK);
					c.add(Calendar.DATE, difference);
					arrayOfDates.add(sd.format(c.getTime()));
					if (hasFirstWeekPassed) {
						c.add(Calendar.WEEK_OF_YEAR, k - 1);
						hasFirstWeekPassed = false;
					}
					l++;
				}
			}
			if (traversedDaysCounter > arrayOfDates.size()) {
				c.add(Calendar.WEEK_OF_YEAR, 1);
				hasFirstWeekPassed = true;
			} else
				c.add(Calendar.WEEK_OF_YEAR, k);
		}

		return arrayOfDates.toArray(new String[arrayOfDates.size()]);
	}

	String[] recurringTaskAltern(String firstDate, int k, String[] daysOfTheWeek, int n) {

		java.util.Map<String, Integer> dayOfWeek0 = new java.util.TreeMap<String, Integer>();
		dayOfWeek0.put("Sunday", java.util.Calendar.SUNDAY);
		dayOfWeek0.put("Monday", java.util.Calendar.MONDAY);
		dayOfWeek0.put("Tuesday", java.util.Calendar.TUESDAY);
		dayOfWeek0.put("Wednesday", java.util.Calendar.WEDNESDAY);
		dayOfWeek0.put("Thursday", java.util.Calendar.THURSDAY);
		dayOfWeek0.put("Friday", java.util.Calendar.FRIDAY);
		dayOfWeek0.put("Saturday", java.util.Calendar.SATURDAY);

		java.util.Map<Integer, String> dayOfWeek1 = new java.util.TreeMap<Integer, String>();
		dayOfWeek1.put(java.util.Calendar.SUNDAY, "Sunday");
		dayOfWeek1.put(java.util.Calendar.MONDAY, "Monday");
		dayOfWeek1.put(java.util.Calendar.TUESDAY, "Tuesday");
		dayOfWeek1.put(java.util.Calendar.WEDNESDAY, "Wednesday");
		dayOfWeek1.put(java.util.Calendar.THURSDAY, "Thursday");
		dayOfWeek1.put(java.util.Calendar.FRIDAY, "Friday");
		dayOfWeek1.put(java.util.Calendar.SATURDAY, "Saturday");

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

		java.util.Set<String> daysOfTheWeekSet = new java.util.HashSet<String>(java.util.Arrays.asList(daysOfTheWeek));

		java.util.List<String> result = new java.util.LinkedList<String>();

		java.util.Date startAt;

		try {
			startAt = sdf.parse(firstDate);
		} catch (ParseException pe) {
			return new String[] { pe.getMessage() };
		}

		java.util.Calendar lCal = new java.util.GregorianCalendar();
		lCal.setTime(startAt);

		java.util.Calendar sCal = new java.util.GregorianCalendar();
		sCal.setTime(startAt);

		int counter = 0;

		while (result.size() < n) {
			if (daysOfTheWeekSet.contains(dayOfWeek1.get(sCal.get(java.util.Calendar.DAY_OF_WEEK)))) {
				result.add(sdf.format(sCal.getTime()));
				counter += 1;
			}
			if (counter == daysOfTheWeek.length) {
				counter = 0;
				lCal.add(java.util.Calendar.DATE, 7 * k);
				sCal.setTime(lCal.getTime());
			} else {
				sCal.add(java.util.Calendar.DATE, 1);
			}
		}

		return result.toArray(new String[] {});
	}

	String[] recurringTaskAltern2(String firstDate, int k, String[] daysOfTheWeek, int n) {

		String[] z = firstDate.split("/");
		int day = Integer.parseInt(z[0]);
		int month = Integer.parseInt(z[1]) - 1;
		int year = Integer.parseInt(z[2]);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = new GregorianCalendar(year, month, day);
		int count = 0;
		int count2 = 0;
		String[] s = new String[n];
		while (count < n) {
			for (String da : daysOfTheWeek) {
				if (count2 / 7 % k == 0
						&& (da.compareTo("Monday") == 0 && c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
								|| da.compareTo("Tuesday") == 0 && c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
								|| da.compareTo("Wednesday") == 0 && c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
								|| da.compareTo("Thursday") == 0 && c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
								|| da.compareTo("Friday") == 0 && c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
								|| da.compareTo("Saturday") == 0 && c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
								|| da.compareTo("Sunday") == 0 && c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
					s[count++] = df.format(c.getTime());
				}
			}
			System.out.println(df.format(c.getTime()));
			c.add(Calendar.DAY_OF_MONTH, 1);
			count2++;
		}
		return s;
	}

}
