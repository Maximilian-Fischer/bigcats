package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.common.base.Objects;
import com.googlecode.objectify.annotation.Subclass;

@Subclass
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
		// preconditions
		assertIsValidLatitude(latitude);
		assertIsValidLongitude(longitude);
		assertIsValidRadius(radiusInKm);

		setLatitude(latitude);
		setLongitude(longitude);
		setRadius(radiusInKm);

		// postconditions
		assertClassInvariants();
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

		// precondition
		assertIsValidLatitude(latitude);

		this.latitude = latitude;

		// postconditions
		assertClassInvariants();
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
		// precondition
		assertIsValidLongitude(longitude);

		this.longitude = longitude;

		// postcondition
		assertClassInvariants();
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

		// precondition
		assertIsValidRadius(radiusInKm);

		this.radius = radiusInKm;

		// postcondition
		assertClassInvariants();

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
		// preconditions
		assertNotNull(secondCoordinate);

		double latitudinalDistance = this.latitude
				- secondCoordinate.getLatitude();
		latitudinalDistance = Math.abs(latitudinalDistance);
		// check for shortest way
		if (latitudinalDistance > 90) {
			latitudinalDistance = 180 - latitudinalDistance;
		}

		// postconditions
		assertIsValidDistance(latitudinalDistance);

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
		// preconditions
		assertNotNull(secondCoordinate);

		double longitudinalDistance = this.longitude
				- secondCoordinate.getLongitude();
		longitudinalDistance = Math.abs(longitudinalDistance);
		// check for shortest way
		if (longitudinalDistance > 180) {
			longitudinalDistance = 360 - longitudinalDistance;
		}

		// postconditions
		assertIsValidDistance(longitudinalDistance);

		return longitudinalDistance;
	}

	/**
	 * @methodtype query
	 */
	@Override
	public double getCartesianX() {
		return radius * Math.cos(Math.toRadians(longitude))
				* Math.sin(Math.toRadians(latitude));
	}

	/**
	 * @methodtype query
	 */
	@Override
	public double getCartesianY() {
		return radius * Math.sin(Math.toRadians(longitude))
				* Math.sin(Math.toRadians(latitude));
	}

	/**
	 * @methodtype query
	 */
	@Override
	public double getCartesianZ() {
		return radius * Math.cos(Math.toRadians(latitude));
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidLatitude(double latitudeValue) {
		if (latitude < -90 || latitude > 90) {
			throw new IllegalArgumentException(
					"Invalid latitude. Value must be between -90 and 90.");
		}
		if (Double.isNaN(latitudeValue)) {
			throw new IllegalArgumentException("latitude must be an number");
		}
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidLongitude(double longitudeValue) {
		if (longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException(
					"Invalid longitude. Value must be between -180 and 180.");
		}
		if (Double.isNaN(longitudeValue)) {
			throw new IllegalArgumentException("longitude must be an number");
		}
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidRadius(double radiusValueInKm) {
		if (radiusValueInKm < 0) {
			throw new IllegalArgumentException("Radius must be greater than 0");
		}
		if (Double.isNaN(radiusValueInKm)) {
			throw new IllegalArgumentException("radius must be a numer");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertClassInvariants() {
		super.assertClassInvariants();
		assertIsValidLatitude(this.latitude);
		assertIsValidLongitude(this.longitude);
		assertIsValidRadius(this.radius);
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
