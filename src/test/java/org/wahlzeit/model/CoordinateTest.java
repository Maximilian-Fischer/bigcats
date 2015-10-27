package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private Coordinate firstShortestDistanceCoordinate;
	private Coordinate secondShortestDistanceCoordinate;
	private Coordinate losAngeles;
	private Coordinate erlangen;

	private static final double distanceErlangenToLosAngelesInKm = 9454.686943807534;
	private static final double shortestDistanceOfDummyCoordinates = 17258.39021528912;
	private static final double DELTA = 0.000001;

	@Before
	public void setUp() {
		erlangen = new Coordinate(49.599937, 11.006300);
		losAngeles = new Coordinate(34.052235, -118.243683);
		firstShortestDistanceCoordinate = new Coordinate(-80.0, 170.0);
		secondShortestDistanceCoordinate = new Coordinate(75.0, -175.0);
	}

	@Test
	public void testDefaultConstructor() {
		Coordinate coordinate = new Coordinate();
		assertEquals(0.0, coordinate.getLatitude(), DELTA);
		assertEquals(0.0, coordinate.getLongitude(), DELTA);
	}

	@Test
	public void testConstructor() {
		Coordinate coordinate = new Coordinate(-32, 100);
		assertEquals(-32, coordinate.getLatitude(), DELTA);
		assertEquals(100, coordinate.getLongitude(), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfHigherBounds() {
		Coordinate coordinate = new Coordinate(90.1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfLowerBounds() {
		Coordinate coordinate = new Coordinate(-90.1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfHigherBounds() {
		Coordinate coordinate = new Coordinate(45, 180.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfLowerBounds() {
		Coordinate coordinate = new Coordinate(45, -180.1);
	}

	@Test
	public void testLatitudinalDistance() {
		double latitudinalDistanceErlangenToLosAngeles = erlangen
				.getLatitudinalDistance(losAngeles);
		double latitudinalDistanceLosAngelesToErlangen = losAngeles
				.getLatitudinalDistance(erlangen);
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
		double longitudinalDistanceErlangenToLosAngeles = erlangen
				.getLongitudinalDistance(losAngeles);
		double longitudinalDistanceLosAngelesToErlangen = losAngeles
				.getLongitudinalDistance(erlangen);
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

	@Test
	public void testDistance() {
		double distanceErlangenToLosAngeles = erlangen.getDistance(losAngeles);
		double distanceLosAngelesToErlangen = losAngeles.getDistance(erlangen);
		double shortestDistanceFirstDummyToSecondDummy = firstShortestDistanceCoordinate
				.getDistance(secondShortestDistanceCoordinate);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceErlangenToLosAngeles, DELTA);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceLosAngelesToErlangen, DELTA);
		assertEquals(shortestDistanceOfDummyCoordinates,
				shortestDistanceFirstDummyToSecondDummy, DELTA);

	}

}
