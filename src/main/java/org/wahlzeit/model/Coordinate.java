package org.wahlzeit.model;

public interface Coordinate {

	public double getDistance(Coordinate otherCoordinate);

	public boolean isEqual(Coordinate otherCoordinate);

}
