package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import com.googlecode.objectify.annotation.Container;

public class BigCat extends DataObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5666489807673383861L;
	private static final int MAXIMUM_CAT_BODYHEIGHT_IN_CM = 400;
	private static final int MAXIMUM_CAT_BODYWEIGHT_IN_KG = 300;
	private static long ID;

	private long bigCatId;
	private String name = "";
	private int bodyheightInCm = 0;
	private int bodyweightInKg = 0;

	@Container
	private BigCatType bigCatType;

	/**
	 * @methodtype constructor
	 */
	public BigCat(BigCatType bigCatType) {
		this.bigCatType = bigCatType;
		bigCatId = ID++;
	}

	/**
	 * @methodtype get
	 */
	public long getId() {
		return bigCatId;
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

	/**
	 * @methodtype get
	 */
	public BigCatType getType() {
		return bigCatType;
	}

	/**
	 * @methodtype get
	 */
	public String getSpecies() {
		return bigCatType.getSpecies();
	}

	/**
	 * @methodtype get
	 */
	public String getHaunt() {
		return bigCatType.getHaunt();
	}

	/**
	 * @methodtype get
	 */
	public long getPopulation() {
		return bigCatType.getPopulation();
	}

}
