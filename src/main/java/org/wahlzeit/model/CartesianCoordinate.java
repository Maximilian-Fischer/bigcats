package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate {

	private double x;
	private double y;
	private double z;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		this(0.0, 0.0, 0.0);
	}

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @param otherCoordinate
	 *            the other Coordinate-Object, gets converted into cartesian
	 *            coordinate object
	 * @return The calculated distance between the coordinates as a
	 *         double-value.
	 * 
	 * @methodtype query
	 * 
	 */
	@Override
	public double getDistance(Coordinate otherCoordinate) {
		CartesianCoordinate otherCoordinateCartesian = asCartesianCoordinate(otherCoordinate);
		double deltaX = this.x - otherCoordinateCartesian.getX();
		double deltaY = this.y - otherCoordinateCartesian.getY();
		double deltaZ = this.z - otherCoordinateCartesian.getZ();

		return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
	}

	/**
	 * @methodtype booleanQuery
	 */
	@Override
	public boolean isEqual(Coordinate otherCoordinate) {
		CartesianCoordinate otherCoordinateCartesian = asCartesianCoordinate(otherCoordinate);
		boolean isXEqual = isDoubleEqual(this.x,
				otherCoordinateCartesian.getX());
		boolean isYEqual = isDoubleEqual(this.y,
				otherCoordinateCartesian.getY());
		boolean isZEqual = isDoubleEqual(this.z,
				otherCoordinateCartesian.getZ());
		return isXEqual && isYEqual && isZEqual;
	}

	/**
	 * @methodtype conversion
	 */
	private CartesianCoordinate asCartesianCoordinate(Coordinate coordinate) {
		if (coordinate instanceof CartesianCoordinate) {
			return (CartesianCoordinate) coordinate;
		} else if (coordinate instanceof SphericCoordinate) {
			return doConversionToCartesianCoordinate((SphericCoordinate) coordinate);
		} else {
			throw new IllegalArgumentException("Unknown coordinate type");
		}
	}

	/**
	 * @methodtype conversion
	 */
	private CartesianCoordinate doConversionToCartesianCoordinate(
			SphericCoordinate coordinate) {

		double latitude = Math.toRadians(coordinate.getLatitude());
		double longitude = Math.toRadians(coordinate.getLongitude());
		double radius = coordinate.getRadius();

		double x = radius * Math.cos(longitude) * Math.sin(latitude);
		double y = radius * Math.sin(longitude) * Math.sin(latitude);
		double z = radius * Math.cos(latitude);

		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartesianCoordinate other = (CartesianCoordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * @methodtype comparison
	 */
	public boolean isDoubleEqual(double value, double valueToCompare) {
		return (Math.abs(value - valueToCompare) < 0.000001);
	}

}
