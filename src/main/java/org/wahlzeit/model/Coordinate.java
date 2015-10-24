package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.common.base.Objects;

public class Coordinate {

	private double latitude;
	private double longitude;

	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitudinalDistance(Coordinate secondCoordinate) {
		double latitudinalDistance = 0.0;
		latitudinalDistance = this.latitude - secondCoordinate.getLatitude();
		latitudinalDistance = Math.abs(latitudinalDistance);
		return latitudinalDistance;
	}

	public double getLongitudinalDistance(Coordinate secondCoordinate) {
		double longitudinalDistance = 0.0;
		longitudinalDistance = this.longitude - secondCoordinate.getLongitude();
		longitudinalDistance = Math.abs(longitudinalDistance);
		return longitudinalDistance;
	}

	public double getDistanceAsDouble(Coordinate secondCoordinate) {
		double latitudinalDistance = getLatitudinalDistance(secondCoordinate);
		double longitudinalDistance = getLongitudinalDistance(secondCoordinate);
		double distance = Math.sqrt(Math.pow(latitudinalDistance, 2)
				+ Math.pow(longitudinalDistance, 2));
		distance = Math.abs(distance);
		return distance;
	}

	public Coordinate getDistance(Coordinate secondCoordinate) {
		double latitudinalDistance = getLatitudinalDistance(secondCoordinate);
		double longitudinalDistance = getLongitudinalDistance(secondCoordinate);
		Coordinate distance = new Coordinate(latitudinalDistance,
				longitudinalDistance);
		return distance;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.latitude, this.longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate otherCoordinate = (Coordinate) obj;
		if (latitude == otherCoordinate.getLatitude()
				&& longitude == otherCoordinate.getLongitude())
			return true;
		else
			return false;
	}
}
