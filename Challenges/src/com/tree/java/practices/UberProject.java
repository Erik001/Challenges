package com.tree.java.practices;

public class UberProject {

	public double[] fareEstimator(int ride_time, int ride_distance, double[] cost_per_minute, double[] cost_per_mile) {

		if (cost_per_minute.length != cost_per_mile.length) {
			return null;
		}

		double[] estimatedFares = new double[cost_per_minute.length];

		for (int a = 0; a < cost_per_minute.length; a++) {
			estimatedFares[a] = (ride_time * cost_per_minute[a] + ride_distance * cost_per_mile[a]);
		}

		return estimatedFares;

	}

	public double perfectCity(double[] departure, double[] destination) {
		double xPath = 0;
		double yPath = 0;
		double vPath = 0;
		for (int i = 0; i < departure.length; i++) {
			if (Math.ceil(departure[i]) == Math.ceil(destination[i])) {
				if (Math.ceil(departure[i]) - departure[i] > destination[i]) {
					vPath = destination[i] + departure[i];
				} else {
					vPath = Math.ceil(destination[i]) - destination[i] + Math.ceil(departure[i]) - departure[i];
				}
			} else {
				vPath = Math.abs(destination[i] - departure[i]);
			}
			if (i == 0) {
				xPath = vPath;
			} else {
				yPath = vPath;
			}

		}

		return xPath + yPath;
	}

	public boolean parkingSpot(int[] carDimensions, int[][] parkingLot, int[] luckySpot) {
		boolean isHorizontal = false;
		boolean isVertical = false;
		if ((luckySpot[2] - luckySpot[0] + 1 == carDimensions[0])
				&& (luckySpot[3] - luckySpot[1] + 1 == carDimensions[1])) {
			isVertical = true;
		} else if (luckySpot[2] - luckySpot[0] + 1 == carDimensions[1]
				&& luckySpot[3] - luckySpot[1] + 1 == carDimensions[0]) {
			isHorizontal = true;
		} else {
			return false;
		}
		int sumParkingSpots = 0;
		int sumRutas = 0;
		if (isVertical) {
			for (int px = luckySpot[0]; px <= luckySpot[2]; px++) {
				for (int py = luckySpot[1]; py <= luckySpot[3]; py++) {
					System.out.print(parkingLot[px][py]);
					if (!(parkingLot[px][py] == 0)) {
						sumParkingSpots++;
					}
				}
				System.out.printf("\n");
			}
			if (sumParkingSpots == 0) {
				for (int px = 0; px < luckySpot[0]; px++) {
					for (int py = luckySpot[1]; py <= luckySpot[3]; py++) {
						System.out.print(parkingLot[px][py]);
						if (!(parkingLot[px][py] == 0)) {
							sumRutas++;
						}
					}
					System.out.printf("\n");
				}
				if (sumRutas != 0) {
					sumRutas = 0;
					for (int px = luckySpot[3] + 1; px < parkingLot.length; px++) {
						for (int py = luckySpot[1]; py <= luckySpot[3]; py++) {
							System.out.print(parkingLot[px][py]);
							if (!(parkingLot[px][py] == 0)) {
								sumRutas++;
							}
						}
						System.out.printf("\n");
					}
				}

			}

		} else {
			for (int px = luckySpot[0]; px <= luckySpot[2]; px++) {
				for (int py = luckySpot[1]; py <= luckySpot[3]; py++) {
					System.out.print(parkingLot[px][py]);
					if (!(parkingLot[px][py] == 0)) {
						sumParkingSpots++;
					}
				}
				System.out.printf("\n");
			}
			if (sumParkingSpots == 0) {
				for (int px = luckySpot[0]; px <= luckySpot[2]; px++) {
					for (int py = 0; py < luckySpot[1]; py++) {
						System.out.print(parkingLot[px][py]);
						if (!(parkingLot[px][py] == 0)) {
							sumRutas++;
						}
					}
					System.out.printf("\n");
				}
				if (sumRutas != 0) {
					sumRutas = 0;
					for (int px = luckySpot[0]; px <= luckySpot[2]; px++) {
						for (int py = luckySpot[3] + 1; py < parkingLot[px].length; py++) {
							System.out.print(parkingLot[px][py]);
							if (!(parkingLot[px][py] == 0)) {
								sumRutas++;
							}
						}
						System.out.printf("\n");
					}
				}
			}

		}
		if (sumParkingSpots + sumRutas == 0)
			return true;
		else
			return false;
	}
}
