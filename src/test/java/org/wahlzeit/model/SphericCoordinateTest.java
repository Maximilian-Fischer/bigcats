package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {

	private SphericCoordinate firstShortestDistanceCoordinate;
	private SphericCoordinate secondShortestDistanceCoordinate;
	private SphericCoordinate sphericLosAngeles;
	private SphericCoordinate sphericErlangen;

	private CartesianCoordinate cartesianErlangen;
	private CartesianCoordinate cartesianLosAngeles;

	// private static final double
	// distanceErlangenToLosAngelesInKmOnEarthSurface =
	// 9454.686943807534;
	// private static final double distanceOfDummyCoordinatesOnEarthSurface =
	// 17258.39021528912;
	private static final double distanceErlangenToLosAngelesInKm = 7712.928747957382;
	private static final double distanceBetweenDummyCoordinatesInKm = 12333.75146622485;
	private static final double DELTA = 0.000001;

	@Before
	public void setUp() {
		sphericErlangen = new SphericCoordinate(49.599937, 11.006300);
		sphericLosAngeles = new SphericCoordinate(34.052235, -118.243683);
		firstShortestDistanceCoordinate = new SphericCoordinate(-80.0, 170.0);
		secondShortestDistanceCoordinate = new SphericCoordinate(75.0, -175.0);

		cartesianErlangen = new CartesianCoordinate(4762.5137725, 926.2823628,
				4129.1772245);
		cartesianLosAngeles = new CartesianCoordinate(-1688.1891415,
				-3142.7037588, 5278.5482385);
	}

	@Test
	public void testDefaultConstructor() {
		SphericCoordinate coordinate = new SphericCoordinate();
		assertEquals(0.0, coordinate.getLatitude(), DELTA);
		assertEquals(0.0, coordinate.getLongitude(), DELTA);
	}

	@Test
	public void testLatLonConstructor() {
		SphericCoordinate coordinate = new SphericCoordinate(-32, 100);
		assertEquals(-32, coordinate.getLatitude(), DELTA);
		assertEquals(100, coordinate.getLongitude(), DELTA);
	}

	@Test
	public void testLatLonConstructorWithRadius() {
		SphericCoordinate coordinate = new SphericCoordinate(-32, 100, 5000);
		assertEquals(-32, coordinate.getLatitude(), DELTA);
		assertEquals(100, coordinate.getLongitude(), DELTA);
		assertEquals(5000, coordinate.getRadius(), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfHigherBounds() {
		SphericCoordinate coordinate = new SphericCoordinate(90.1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfLowerBounds() {
		SphericCoordinate coordinate = new SphericCoordinate(-90.1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfHigherBounds() {
		SphericCoordinate coordinate = new SphericCoordinate(45, 180.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfLowerBounds() {
		SphericCoordinate coordinate = new SphericCoordinate(45, -180.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRadiusOutOfBounds() {
		SphericCoordinate coordinate = new SphericCoordinate(45, 40, -20);
	}

	@Test
	public void testLatitudinalDistance() {
		double latitudinalDistanceErlangenToLosAngeles = sphericErlangen
				.getLatitudinalDistance(sphericLosAngeles);
		double latitudinalDistanceLosAngelesToErlangen = sphericLosAngeles
				.getLatitudinalDistance(sphericErlangen);
		assertEquals(15.547702, latitudinalDistanceErlangenToLosAngeles, DELTA);
		assertEquals(15.547702, latitudinalDistanceLosAngelesToErlangen, DELTA);
	}

	@Test
	public void testShortestLatitdinalDistance() {
		double shortestLatitudinalDistance = firstShortestDistanceCoordinate
				.getLatitudinalDistance(secondShortestDistanceCoordinate);
		assertEquals(25.0, shortestLatitudinalDistance, DELTA);

	}

	@Test
	public void testLongitudinalDistance() {
		double longitudinalDistanceErlangenToLosAngeles = sphericErlangen
				.getLongitudinalDistance(sphericLosAngeles);
		double longitudinalDistanceLosAngelesToErlangen = sphericLosAngeles
				.getLongitudinalDistance(sphericErlangen);
		assertEquals(129.249983, longitudinalDistanceErlangenToLosAngeles,
				DELTA);
		assertEquals(129.249983, longitudinalDistanceLosAngelesToErlangen,
				DELTA);
	}

	@Test
	public void testShortestLongitudinalDistance() {
		double shortestLongitudinalDistance = firstShortestDistanceCoordinate
				.getLongitudinalDistance(secondShortestDistanceCoordinate);
		assertEquals(15.0, shortestLongitudinalDistance, DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudinalDistanceWithNull() {
		double latitudinalDistanceElangenSphericToNull = sphericErlangen
				.getLatitudinalDistance(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudinalDistanceWithNull() {
		double longitudinalDistanceElangenSphericToNull = sphericErlangen
				.getLongitudinalDistance(null);
	}

	@Test
	public void testDistance() {
		double distanceErlangenToLosAngeles = sphericErlangen
				.getDistance(sphericLosAngeles);
		double distanceLosAngelesToErlangen = sphericLosAngeles
				.getDistance(sphericErlangen);
		double shortestDistanceFirstDummyToSecondDummy = firstShortestDistanceCoordinate
				.getDistance(secondShortestDistanceCoordinate);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceErlangenToLosAngeles, DELTA);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceLosAngelesToErlangen, DELTA);
		assertEquals(distanceBetweenDummyCoordinatesInKm,
				shortestDistanceFirstDummyToSecondDummy, DELTA);

	}

	@Test
	public void testDistanceWithConversionOfSameLocation() {
		double distanceElangenSphericToErlangenCartesian = sphericErlangen
				.getDistance(cartesianErlangen);
		double distanceErlangenCartesianToErlangenSpheric = cartesianErlangen
				.getDistance(sphericErlangen);
		assertEquals(0.0, distanceElangenSphericToErlangenCartesian, DELTA);
		assertEquals(0.0, distanceErlangenCartesianToErlangenSpheric, DELTA);
	}

	@Test
	public void testDistanceWithConversionOfDifferendLocation() {
		double distanceErlangenSphericToLosAngelesCartesian = sphericErlangen
				.getDistance(cartesianLosAngeles);
		double distanceLosAngelesSphericToErlangenCartesian = sphericLosAngeles
				.getDistance(cartesianErlangen);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceErlangenSphericToLosAngelesCartesian, DELTA);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceLosAngelesSphericToErlangenCartesian, DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceOfSphericWithNullAsParameter() {
		double distanceElangenSphericToErlangenCartesian = sphericErlangen
				.getDistance(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceOfCartesianWithNullAsParameter() {
		double distanceErlangenCartesianToErlangenSpheric = cartesianErlangen
				.getDistance(null);
	}

}
