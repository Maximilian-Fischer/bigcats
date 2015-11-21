package org.wahlzeit.model;

public interface Coordinate {

	/**
	 * @methodtype query
	 */
	double getDistance(Coordinate otherCoordinate);

	/**
	 * @methodtype comparison
	 */
	boolean isEqual(Coordinate otherCoordinate);

	/**
	 * @methodtype query
	 */
	double getCartesianX();

	/**
	 * @methodtype query
	 */
	double getCartesianY();

	/**
	 * @methodtype query
	 */
	double getCartesianZ();
}
