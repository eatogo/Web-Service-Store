package _00_utility.model;

public class Place {
	private String requestIdentity = null;
	private Double latitude = null;
	private Double longitude = null;
	
	public Place(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Place(String requestIdentity, Double latitude, Double longitude) {
		this.requestIdentity = requestIdentity;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getRequestIdentity() {
		return requestIdentity;
	}

	public void setRequestIdentity(String requestIdentity) {
		this.requestIdentity = requestIdentity;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
