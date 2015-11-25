package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CartesianCoordinate extends AbstractCoordinate {

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
		// preconditions
		assertIsValidX(x);
		assertIsValidY(y);
		assertIsValidZ(z);

		this.x = x;
		this.y = y;
		this.z = z;

		// postconditions
		assertClassInvariants();
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
	@Override
	public double getCartesianX() {
		return x;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double getCartesianY() {
		return y;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double getCartesianZ() {
		return z;
	}

	/**
	 * @methodtype set
	 */
	public void setX(double x) {
		// preconditions
		assertIsValidX(x);

		this.x = x;

		// postconditions
		assertClassInvariants();
	}

	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		// preconditions
		assertIsValidX(y);

		this.y = y;

		// postconditions
		assertClassInvariants();
	}

	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		// preconditions
		assertIsValidX(z);

		this.z = z;

		// postconditions
		assertClassInvariants();
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidX(double xValue) {
		if (Double.isNaN(xValue)) {
			throw new IllegalArgumentException("x-coordinate must be a number");
		}
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidY(double yValue) {
		if (Double.isNaN(yValue)) {
			throw new IllegalArgumentException("y-coordinate must be a numer");
		}
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidZ(double zValue) {
		if (Double.isNaN(zValue)) {
			throw new IllegalArgumentException("z-coordinate must be a numer");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertClassInvariants() {
		super.assertClassInvariants();
		assertIsValidX(this.x);
		assertIsValidY(this.y);
		assertIsValidZ(this.z);
	}
}
