package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class BigCatManager {

	private static BigCatManager instance;

	private final HashMap<String, BigCatType> bigCatTypes = new HashMap<String, BigCatType>();
	private final HashMap<Long, BigCat> bigCats = new HashMap<Long, BigCat>();

	/**
	 * @methodtype constructor
	 */
	private BigCatManager() {
		doCreateBasicTypes();
	}

	/**
	 * @methodtype getter
	 */
	public static BigCatManager getInstance() {
		if (instance == null) {
			instance = new BigCatManager();
		}
		return instance;
	}

	/**
	 * @methodtype factory
	 */
	public synchronized BigCat createBigCat(String species) {
		assertIsBigCatTypeAvailable(species);
		BigCatType bigCatType = doGetBigCatType(species);
		BigCat result = bigCatType.createInstance();
		bigCats.put(result.getId(), result);
		return result;
	}

	/**
	 * @methodtype factory
	 */
	public synchronized BigCatType createBigCatType(String species,
			String haunt, long population) {
		assertIsBigCatTypeNotYetCreated(species);
		BigCatType result = new BigCatType(species, haunt, population);
		bigCatTypes.put(species, result);
		return result;
	}

	/**
	 * @methodtype mutation method
	 */
	public BigCatType changeBigCatType(String species, String haunt,
			long population) {
		assertIsBigCatTypeAvailable(species);
		bigCatTypes.remove(species);
		return createBigCatType(species, haunt, population);
	}

	/**
	 * @methodtype getter
	 */
	private BigCatType doGetBigCatType(String species) {
		return bigCatTypes.get(species);
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsBigCatTypeAvailable(String species) {
		if (bigCatTypes.get(species) == null) {
			throw new IllegalArgumentException("BigCatType " + species
					+ " not available");
		}
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsBigCatTypeNotYetCreated(String species) {
		if (bigCatTypes.get(species) != null) {
			throw new IllegalArgumentException("BigCatType " + species
					+ " already exists");
		}
	}

	/**
	 * @methodtype helper / factory
	 */
	private void doCreateBasicTypes() {
		createBigCatType("Tiger", "East Russia", 2000);
		createBigCatType("Lion", "Desert", 16000);
	}

	/**
	 * @methodtype command method
	 */
	public void deleteBigCat(long bigCatId) {
		bigCats.remove(bigCatId);
	}

	/**
	 * @methodtype command method
	 */
	public void deleteBigCatType(String species) {
		bigCatTypes.remove(species);
	}

	/**
	 * @methodtype query method
	 */
	public List<BigCat> getAllInstancesOfType(String species) {
		List<BigCat> resultList = new ArrayList<BigCat>();
		for (Entry<Long, BigCat> entry : bigCats.entrySet()) {
			if (entry.getValue().getSpecies().equals(species)) {
				resultList.add(entry.getValue());
			}
		}
		return resultList;
	}
}
