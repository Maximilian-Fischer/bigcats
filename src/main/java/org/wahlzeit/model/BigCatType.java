package org.wahlzeit.model;

public class BigCatType {

	private String species = "";
	private String haunt = "";
	private long population;

	/**
	 * @methodtype constructor
	 */
	public BigCatType(String species, String haunt, long population) {
		this.species = species;
		this.haunt = haunt;
		this.population = population;
	}

	/**
	 * @methodtype get
	 */
	public String getSpecies() {
		return species;
	}

	/**
	 * @methodtype get
	 */
	public String getHaunt() {
		return haunt;
	}

	/**
	 * @methodtype get
	 */
	public long getPopulation() {
		return population;
	}

	/**
	 * @methodtype booleanQuery
	 */
	public boolean isInstance(BigCat bigCat) {
		return bigCat.getType() == this;
	}

	/**
	 * @methodtype factory method
	 */
	public BigCat createInstance() {
		return new BigCat(this);
	}

}
