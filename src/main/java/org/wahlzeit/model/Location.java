package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

public class Location {

	private String name;
	@Container
	private Coordinate coordinate;

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
	public Location(Coordinate coordinate) {
		this(coordinate, "");
	}

	/**
	 * @methodtype constructor
	 */
	public Location(Coordinate coordinate, String name) {
		assertIsValidCoordinate(coordinate);
		assertIsValidName(name);
		this.coordinate = coordinate;
		this.name = name;
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidCoordinate(Coordinate coordinate) {
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
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

}
