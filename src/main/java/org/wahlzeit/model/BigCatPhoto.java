package org.wahlzeit.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BigCatPhoto extends Photo {

	private static final long serialVersionUID = 6761837684553577495L;

	@Container
	private Date timeTaken;
	@Container
	private BigCat bigCat;

	/**
	 * @methodtype constructor
	 */
	public BigCatPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public BigCatPhoto(PhotoId id) {
		super(id);
	}

	/**
	 * @methodtype constructor
	 */
	public BigCatPhoto(PhotoId id, Location location) {
		super(id, location);
	}

	/**
	 * @methodtype constructor
	 */
	public BigCatPhoto(PhotoId id, Location location, String species) {
		super(id, location);
		bigCat = BigCatManager.getInstance().createBigCat(species);
	}

	/**
	 * @methodtype getter
	 */
	public BigCat getBigCat() {
		return bigCat;
	}

	/**
	 * @methodtype getter
	 */
	public Date getTimeTaken() {
		return timeTaken;
	}

	/**
	 * @methodtype setter
	 */
	public void setTimeTaken(Date timeTaken) {
		this.timeTaken = timeTaken;
	}

}
