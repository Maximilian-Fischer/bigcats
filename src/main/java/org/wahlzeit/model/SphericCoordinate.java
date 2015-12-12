package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.common.base.Objects;
import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class SphericCoordinate extends AbstractCoordinate {

	private double latitude;
	private double longitude;
	private double radius;

	static final int EARTH_RADIUS_IN_KM = 6371;
	static final double DEFAULT_LATITUDE = 0.0;
	static final double DEFAULT_LONGITUDE = 0.0;

	/**
	 * @methodtype constructor
	 */
	private SphericCoordinate() {
		this(DEFAULT_LATITUDE, DEFAULT_LONGITUDE, EARTH_RADIUS_IN_KM);
	}

	/**
	 * @methodtype constructor
	 */
	private SphericCoordinate(double latitude, double longitude) {
		this(latitude, longitude, EARTH_RADIUS_IN_KM);
	}

	/**
	 * @methodtype constructor
	 */
	private SphericCoordinate(double latitude, double longitude,
			double radiusInKm) {
		// preconditions
		assertIsValidLatitude(latitude);
		assertIsValidLongitude(longitude);
		assertIsValidRadius(radiusInKm);

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radiusInKm;

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
	public SphericCoordinate setLatitude(double latitude) {

		// precondition
		assertIsValidLatitude(latitude);

		SphericCoordinate result = getInstance(latitude, this.longitude,
				this.radius);

		// postconditions
		assertClassInvariants();
		return result;
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
	public SphericCoordinate setLongitude(double longitude) {
		// precondition
		assertIsValidLongitude(longitude);

		SphericCoordinate result = getInstance(this.latitude, longitude,
				this.radius);

		// postcondition
		assertClassInvariants();
		return result;
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
	public SphericCoordinate setRadius(double radiusInKm) {

		// precondition
		assertIsValidRadius(radiusInKm);

		SphericCoordinate result = getInstance(this.latitude, this.longitude,
				radiusInKm);

		// postcondition
		assertClassInvariants();
		return result;

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
		return Objects.hashCode(this.latitude, this.longitude, this.radius);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof SphericCoordinate))
			return false;
		SphericCoordinate otherCoordinate = (SphericCoordinate) obj;
		if (latitude == otherCoordinate.getLatitude()
				&& longitude == otherCoordinate.getLongitude()
				&& radius == otherCoordinate.getRadius())
			return true;
		else
			return false;
	}

	/**
	 * @methodtype helper
	 */
	public static SphericCoordinate getInstance(double latitude,
			double longitude, double radius) {

		// preconditions
		assertParameterNotNull(latitude);
		assertParameterNotNull(longitude);
		assertParameterNotNull(radius);

		String keyString = doCreateKeyString(latitude, longitude, radius,
				SphericCoordinate.class.getCanonicalName());
		Coordinate result = coordinateInstances.get(keyString);
		if (result == null) {
			synchronized (coordinateInstances) {
				result = coordinateInstances.get(keyString);
				if (result == null) {
					result = new SphericCoordinate(latitude, longitude, radius);
					coordinateInstances.put(keyString, result);
				}
			}
		}
		// postcondition
		assertParameterNotNull(result);
		return (SphericCoordinate) result;
	}

}
