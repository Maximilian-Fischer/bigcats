package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * returns direct distance between two coordinate objects (interchangeably)
	 * 
	 * @methodtype query
	 * 
	 */
	@Override
	public double getDistance(Coordinate otherCoordinate) {
		assertNotNull(otherCoordinate);
		CartesianCoordinate thisCoordinateCartesian = this
				.asCartesianCoordinate();
		CartesianCoordinate otherCoordinateCartesian = ((AbstractCoordinate) otherCoordinate)
				.asCartesianCoordinate();
		double deltaX = thisCoordinateCartesian.getX()
				- otherCoordinateCartesian.getX();
		double deltaY = thisCoordinateCartesian.getY()
				- otherCoordinateCartesian.getY();
		double deltaZ = thisCoordinateCartesian.getZ()
				- otherCoordinateCartesian.getZ();
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
	}

	/**
	 * @methodtype booleanQuery
	 */
	@Override
	public boolean isEqual(Coordinate otherCoordinate) {
		assertNotNull(otherCoordinate);
		CartesianCoordinate thisCoordinateCartesian = this
				.asCartesianCoordinate();
		CartesianCoordinate otherCoordinateCartesian = ((AbstractCoordinate) otherCoordinate)
				.asCartesianCoordinate();
		boolean isXEqual = isDoubleEqual(thisCoordinateCartesian.getX(),
				otherCoordinateCartesian.getX());
		boolean isYEqual = isDoubleEqual(thisCoordinateCartesian.getY(),
				otherCoordinateCartesian.getY());
		boolean isZEqual = isDoubleEqual(thisCoordinateCartesian.getZ(),
				otherCoordinateCartesian.getZ());
		return isXEqual && isYEqual && isZEqual;
	}

	/**
	 * @methodtype convertion
	 */
	protected abstract CartesianCoordinate asCartesianCoordinate();

	/**
	 * @methodtype assertion
	 */
	private void assertNotNull(Coordinate otherCoordinate) {
		if (otherCoordinate == null) {
			throw new IllegalArgumentException(
					"other Coordinate can not be null");
		}
	}

	/**
	 * @methodtype comparison
	 */
	protected boolean isDoubleEqual(double value, double valueToCompare) {
		return (Math.abs(value - valueToCompare) < 0.000001);
	}

}
