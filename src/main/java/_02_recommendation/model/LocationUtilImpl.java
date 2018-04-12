package _02_recommendation.model;

import _00_global.model.Place;
import _00_global.utility.LocationUtil;

/**
 * 此類別利用兩地經緯度計算距離
 */
public class LocationUtilImpl implements LocationUtil {
	
	public LocationUtilImpl() {
	}

	/**
	 * 回傳的double值單位為km
	 */
	@Override
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
		// 此處計算出來的distance單位為mile
		distance = distance * 60 * 1.1515;
		// 將單位從mile轉為km
		// 若改為乘以0.8684則是轉為海哩nautica mile
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
