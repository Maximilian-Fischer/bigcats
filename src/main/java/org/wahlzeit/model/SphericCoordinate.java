package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.common.base.Objects;

public class SphericCoordinate implements Coordinate {

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
	 * Calculates the distance of two coordinates great-circle distance formula,
	 * see {@link https ://en.wikipedia.org/wiki/Great-circle_distance} for
	 * details converts
	 * 
	 * @param otherCoordinate
	 *            the other Coordinate-Object, gets converted into spheric
	 *            coordinate object
	 * @return The calculated distance between the coordinates as a
	 *         double-value.
	 * 
	 * @methodtype query
	 * 
	 */
	@Override
	public double getDistance(Coordinate otherCoordinate) {

		SphericCoordinate otherCoordinateSpheric = asSphericCoordinate(otherCoordinate);
		double distance = 0.0;
		double latFirstCoordinateAsRadiant = Math.toRadians(this.latitude);
		double latSecondCoordinateAsRadiant = Math
				.toRadians(otherCoordinateSpheric.getLatitude());
		double longitudinalDistanceAsRadiant = Math
				.toRadians(getLongitudinalDistance(otherCoordinateSpheric));

		distance = Math.sin(latFirstCoordinateAsRadiant)
				* Math.sin(latSecondCoordinateAsRadiant)
				+ Math.cos(latFirstCoordinateAsRadiant)
				* Math.cos(latSecondCoordinateAsRadiant)
				* Math.cos(longitudinalDistanceAsRadiant);
		distance = this.radius * Math.acos(distance);
		return distance;
	}

	/**
	 * @methodtype conversion
	 */
	private SphericCoordinate asSphericCoordinate(Coordinate coordinate) {
		if (coordinate instanceof SphericCoordinate) {
			return (SphericCoordinate) coordinate;
		} else if (coordinate instanceof CartesianCoordinate) {
			return doConversionToSphericCoordinate((CartesianCoordinate) coordinate);
		} else {
			throw new IllegalArgumentException("Unknown coordinate type");
		}
	}

	/**
	 * @methodtype conversion
	 */
	private SphericCoordinate doConversionToSphericCoordinate(
			CartesianCoordinate coordinate) {
		double x = coordinate.getX();
		double y = coordinate.getY();
		double z = coordinate.getZ();

		double r = Math.sqrt(x * x + y * y + z * z);
		double latitude = Math.toDegrees(Math.acos(z / r));
		double longitude = Math.toDegrees(Math.atan2(y, x));

		return new SphericCoordinate(latitude, longitude, r);
	}

	/**
	 * @methodtype booleanQuery
	 */
	@Override
	public boolean isEqual(Coordinate otherCoordinate) {
		SphericCoordinate otherCoordinateSpheric = asSphericCoordinate(otherCoordinate);
		boolean isLatitudeEqual = isDoubleEqual(latitude,
				otherCoordinateSpheric.getLatitude());
		boolean isLongitudeEqual = isDoubleEqual(longitude,
				otherCoordinateSpheric.getLongitude());
		boolean isRadiusEqual = isDoubleEqual(radius,
				otherCoordinateSpheric.getRadius());
		return isLatitudeEqual && isLongitudeEqual && isRadiusEqual;
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

	/**
	 * @methodtype comparison
	 */
	public boolean isDoubleEqual(double value, double valueToCompare) {
		return (Math.abs(value - valueToCompare) < 0.000001);
	}

}
