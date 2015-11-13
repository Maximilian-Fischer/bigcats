package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.common.base.Objects;

public class SphericCoordinate extends AbstractCoordinate {

	private double latitude;
	private double longitude;
	private double radius;

	private static final int EARTH_RADIUS_IN_KM = 6371;
	static final double DEFAULT_LATITUDE = 0.0;
	static final double DEFAULT_LONGITUDE = 0.0;

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate() {
		this(DEFAULT_LATITUDE, DEFAULT_LONGITUDE, EARTH_RADIUS_IN_KM);
	}

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude) {
		this(latitude, longitude, EARTH_RADIUS_IN_KM);
	}

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude,
			double radiusInKm) {
		setLatitude(latitude);
		setLongitude(longitude);
		setRadius(radiusInKm);
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
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @methodtype set
	 */
	public void setRadius(double radiusInKm) {
		if (radiusInKm < 0) {
			throw new IllegalArgumentException("Radius must be greater than 0");
		} else {
			this.radius = radiusInKm;
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
	public double getLatitudinalDistance(SphericCoordinate secondCoordinate) {
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
	public double getLongitudinalDistance(SphericCoordinate secondCoordinate) {
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
	 * @methodtype conversion
	 */
	@Override
	protected CartesianCoordinate asCartesianCoordinate() {

		double latitudeAsRadiant = Math.toRadians(this.latitude);
		double longitudeAsRadiant = Math.toRadians(this.longitude);

		double x = this.radius * Math.cos(longitudeAsRadiant)
				* Math.sin(latitudeAsRadiant);
		double y = this.radius * Math.sin(longitudeAsRadiant)
				* Math.sin(latitudeAsRadiant);
		double z = this.radius * Math.cos(latitudeAsRadiant);

		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.latitude, this.longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof SphericCoordinate))
			return false;
		SphericCoordinate otherCoordinate = (SphericCoordinate) obj;
		if (latitude == otherCoordinate.getLatitude()
				&& longitude == otherCoordinate.getLongitude())
			return true;
		else
			return false;
	}

}
