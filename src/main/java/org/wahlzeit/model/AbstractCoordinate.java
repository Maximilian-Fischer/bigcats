package org.wahlzeit.model;

import java.util.HashMap;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public abstract class AbstractCoordinate implements Coordinate {

	protected static final HashMap<String, Coordinate> coordinateInstances = new HashMap<String, Coordinate>();

	/**
	 * returns direct distance between two coordinate objects (interchangeably)
	 * 
	 * @methodtype query
	 * 
	 */
	@Override
	public double getDistance(Coordinate otherCoordinate) {
		// preconditions
		assertNotNull(otherCoordinate);

		double deltaX = getCartesianX() - otherCoordinate.getCartesianX();
		double deltaY = getCartesianY() - otherCoordinate.getCartesianY();
		double deltaZ = getCartesianZ() - otherCoordinate.getCartesianZ();
		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
				* deltaZ);

		// postconditions
		assertIsValidDistance(distance);
		assertClassInvariants();

		return distance;
	}

	/**
	 * @methodtype helper method
	 * 
	 */
	protected static String doCreateKeyString(double paramOne, double paramTwo,
			double paramThree, String className) {

		String result = "" + paramOne + " " + paramTwo + " " + paramThree + " "
				+ className;
		return result;
	}

	/**
	 * @methodtype booleanQuery
	 */
	@Override
	public boolean isEqual(Coordinate otherCoordinate) {
		// preconditions
		assertNotNull(otherCoordinate);

		boolean isXEqual = isDoubleEqual(getCartesianX(),
				otherCoordinate.getCartesianX());
		boolean isYEqual = isDoubleEqual(getCartesianY(),
				otherCoordinate.getCartesianY());
		boolean isZEqual = isDoubleEqual(getCartesianZ(),
				otherCoordinate.getCartesianZ());
		boolean isEverythingEqual = isXEqual && isYEqual && isZEqual;

		// postconditions
		assertClassInvariants();

		return isEverythingEqual;
	}

	/**
	 * @methodtype booleanQuery
	 */
	public boolean isSame(Coordinate otherCoordinate) {
		// preconditions
		assertNotNull(otherCoordinate);

		boolean isSameCoordinateObject = (this == otherCoordinate);

		// postconditions
		assertClassInvariants();

		return isSameCoordinateObject;
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertNotNull(Coordinate otherCoordinate) {
		if (otherCoordinate == null) {
			throw new IllegalArgumentException(
					"other Coordinate can not be null");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsValidDistance(double distance) {
		if (distance < 0)
			throw new IllegalArgumentException(
					"distance needs to be greater or equal zero");
		if (Double.isNaN(distance))
			throw new IllegalArgumentException("distance needs to be a number");
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertClassInvariants() {
		// AbstractCoordinate does not have a state --> no invariant checks
		// needed
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected static void assertParameterNotNull(Object other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter should not be null.");
		}
	}

	/**
	 * @methodtype comparison
	 */
	protected boolean isDoubleEqual(double value, double valueToCompare) {
		return (Math.abs(value - valueToCompare) < 0.000001);
	}

}
