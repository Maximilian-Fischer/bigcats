package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

public class BigCatsPhotoFactory extends PhotoFactory {

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static PhotoFactory instance = null;

	/**
	 * @methodtype constructor
	 */
	private BigCatsPhotoFactory() {
		// nothing to do here ;)
	}

	/**
	 * Public singleton access method.
	 * 
	 * methodtype get
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage()
					.addAction("setting generic PhotoFactory").toString());
			setInstance(new BigCatsPhotoFactory());
		}
		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 * 
	 * @ methodtype set
	 */
	protected static synchronized void setInstance(PhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException(
					"attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new BigCatsPhoto();
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new BigCatsPhoto(id);
	}

	/**
	 * @methodtype factory
	 */
	public Photo createPhoto(PhotoId id, double latitude, double longitude) {
		return new BigCatsPhoto(id, latitude, longitude);
	}

}
