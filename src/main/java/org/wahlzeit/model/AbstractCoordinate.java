package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public abstract class AbstractCoordinate extends DataObject implements
		Coordinate {

	/**
	 * returns direct distance between two coordinate objects (interchangeably)
	 * 
	 * @methodtype query
	 * 
	 */

	@Id
	Long idLong;
	@Parent
	Key parent = ObjectManager.applicationRootKey;

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
	 * @methodtype comparison
	 */
	protected boolean isDoubleEqual(double value, double valueToCompare) {
		return (Math.abs(value - valueToCompare) < 0.000001);
	}

}
