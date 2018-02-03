package _00_utility;

import _00_utility.model.Place;

public class LocationUtil {

	/*
	 * 回傳的distance單位為km latitude
	 */
	public double distance(Place firstSpot, Place secondSpot) {
		double firstLatitude = firstSpot.getLatitude();
		double firstLongitude = firstSpot.getLongitude();
		double secondLatitude = secondSpot.getLatitude();
		double secondLongitude = secondSpot.getLongitude();
		double theta = firstLongitude - secondLongitude;
		double distance = Math.sin(deciamlDegreeToRadians(firstLatitude))
				* Math.sin(deciamlDegreeToRadians(secondLatitude))
				+ Math.cos(deciamlDegreeToRadians(firstLatitude)) * Math.cos(deciamlDegreeToRadians(secondLatitude))
						* Math.cos(deciamlDegreeToRadians(theta));
		distance = Math.acos(distance);
		distance = radiansToDecimalDegree(distance);
		distance = distance * 60 * 1.1515;
		// 將單位從mile轉為km
		// 若改成以0.8684則是轉為海哩nautica mile
		distance = distance * 1.609344;
		return (distance);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians :: */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double deciamlDegreeToRadians(double decimalDegree) {
		return (decimalDegree * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees :: */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double radiansToDecimalDegree(double radians) {
		return (radians * 180.0 / Math.PI);
	}

}
