package org.wahlzeit.model;

public class Location {

	private String name;
	private SphericCoordinate coordinate;

	/**
	 * @methodtype constructor
	 */
	public Location() {
		this(new SphericCoordinate(SphericCoordinate.DEFAULT_LATITUDE,
				SphericCoordinate.DEFAULT_LONGITUDE));
	}

	/**
	 * @methodtype constructor
	 */
	public Location(SphericCoordinate coordinate) {
		this(coordinate, "");
	}

	/**
	 * @methodtype constructor
	 */
	public Location(SphericCoordinate coordinate, String name) {
		assertIsValidCoordinate(coordinate);
		assertIsValidName(name);
		this.coordinate = coordinate;
		this.name = name;
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidCoordinate(SphericCoordinate coordinate) {
		if (coordinate == null)
			throw new IllegalArgumentException("coordinate should not be null");
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidName(String name) {
		if (name == null)
			throw new IllegalArgumentException("name should not be null");
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @methodtype get
	 */
	public SphericCoordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @methodtype set
	 */
	public void setCoordinate(SphericCoordinate coordinate) {
		this.coordinate = coordinate;
	}

}
