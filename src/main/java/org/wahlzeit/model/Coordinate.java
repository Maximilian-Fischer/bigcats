package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.common.base.Objects;

public class Coordinate {

	private double latitude;
	private double longitude;

	private static final int EARTH_RADIUS_IN_KM = 6371;
	static final double DEFAULT_LATITUDE = 0.0;
	static final double DEFAULT_LONGITUDE = 0.0;

	/**
	 * @methodtype constructor
	 */
	public Coordinate() {
		this(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
	}

	/**
	 * @methodtype constructor
	 */
	public Coordinate(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @methodtype set
	 */
	public void setLatitude(double latitude) {
		if (latitude < -90 || latitude > 90) {
			throw new IllegalArgumentException(
					"Invalid latitude. Value must be between -90 and 90.");
		} else {
			this.latitude = latitude;
		}
	}

	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @methodtype set
	 */
	public void setLongitude(double longitude) {
		if (longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException(
					"Invalid longitude. Value must be between -180 and 180.");
		} else {
			this.longitude = longitude;
		}
	}

	/**
	 * @param other
	 *            the other Coordinate-Object
	 * @return distance between the latitude-values of both coordinates as
	 *         double-value
	 * 
	 * @methodtype query
	 * 
	 */
	public double getLatitudinalDistance(Coordinate secondCoordinate) {
		double latitudinalDistance = this.latitude
				- secondCoordinate.getLatitude();
		latitudinalDistance = Math.abs(latitudinalDistance);
		// check for shortest way
		if (latitudinalDistance > 90) {
			latitudinalDistance = 180 - latitudinalDistance;
		}
		return latitudinalDistance;
	}

	/**
	 * @param other
	 *            the other Coordinate-Object
	 * @return distance between the longitude-values of both coordinates as
	 *         double-value
	 * 
	 * @methodtype query
	 * 
	 */
	public double getLongitudinalDistance(Coordinate secondCoordinate) {
		double longitudinalDistance = this.longitude
				- secondCoordinate.getLongitude();
		longitudinalDistance = Math.abs(longitudinalDistance);
		// check for shortest way
		if (longitudinalDistance > 180) {
			longitudinalDistance = 360 - longitudinalDistance;
		}
		return longitudinalDistance;
	}

	/**
	 * Calculates the distance of two coordinates great-circle distance formula,
	 * see {@link https://en.wikipedia.org/wiki/Great-circle_distance} for
	 * details
	 * 
	 * @param other
	 *            the other Coordinate-Object
	 * @return The calculated distance between the coordinates as a
	 *         double-value.
	 * 
	 * @methodtype query
	 * 
	 */
	public double getDistance(Coordinate secondCoordinate) {
		double distance = 0.0;
		double latFirstCoordinateAsRadiant = Math.toRadians(this.latitude);
		double latSecondCoordinateAsRadiant = Math.toRadians(secondCoordinate
				.getLatitude());
		double longitudinalDistanceAsRadiant = Math
				.toRadians(getLongitudinalDistance(secondCoordinate));

		distance = Math.sin(latFirstCoordinateAsRadiant)
				* Math.sin(latSecondCoordinateAsRadiant)
				+ Math.cos(latFirstCoordinateAsRadiant)
				* Math.cos(latSecondCoordinateAsRadiant)
				* Math.cos(longitudinalDistanceAsRadiant);
		distance = EARTH_RADIUS_IN_KM * Math.acos(distance);
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
