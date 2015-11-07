package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BigCatsPhoto extends Photo {

	private static final long serialVersionUID = 6761837684553577495L;
	private static final int MAXIMUM_CAT_BODYHEIGHT_IN_CM = 400;
	private static final int MAXIMUM_CAT_BODYWEIGHT_IN_KG = 300;

	private String species = "";
	private int bodyheightInCm = 0;
	private int bodyweightInKg = 0;

	/**
	 * @methodtype constructor
	 */
	public BigCatsPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public BigCatsPhoto(PhotoId id) {
		super(id);
	}

	/**
	 * @methodtype constructor
	 */
	public BigCatsPhoto(PhotoId id, String species) {
		super(id);
		this.species = species;
	}

	public BigCatsPhoto(PhotoId id, Location location) {
		super(id, location);
	}

	public BigCatsPhoto(PhotoId id, Location location, String species) {
		super(id, location);
		this.species = species;
	}

	/**
	 * @methodtype get
	 */
	public String getSpecies() {
		return species;
	}

	/**
	 * @methodtype set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * @methodtype get
	 */
	public int getBodyheightInCm() {
		return bodyheightInCm;
	}

	/**
	 * @methodtype set
	 */
	public void setBodyheightInCm(int bodyheightInCm) {
		assertIsBodyheightValid(bodyheightInCm);
		this.bodyheightInCm = bodyheightInCm;
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsBodyheightValid(int bodyheightInCm) {
		if (bodyheightInCm <= 0
				|| bodyheightInCm > MAXIMUM_CAT_BODYHEIGHT_IN_CM)
			throw new IllegalArgumentException(
					"There is no big cat with this bodyheight");
	}

	/**
	 * @methodtype get
	 */
	public int getBodyweightInKg() {
		return bodyweightInKg;
	}

	/**
	 * @methodtype set
	 */
	public void setBodyweightInKg(int bodyweightInKg) {
		assertIsBodyweightValid(bodyweightInKg);
		this.bodyweightInKg = bodyweightInKg;
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsBodyweightValid(int bodyweightInKg) {
		if (bodyweightInKg <= 0
				|| bodyweightInKg > MAXIMUM_CAT_BODYWEIGHT_IN_KG)
			throw new IllegalArgumentException(
					"There is no big cat with this bodyweight");
	}
}
