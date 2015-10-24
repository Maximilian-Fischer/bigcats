package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private Coordinate firstCoordinate;
	private Coordinate secondCoordinate;
	private final static double DELTA = 0.000001;

	@Before
	public void setUp() {
		firstCoordinate = new Coordinate(4.0, 4.0);
		secondCoordinate = new Coordinate(2.0, 1.0);
	}

	@Test
	public void testLatitudinalDistance() {
		double distanceFirstToSecond = firstCoordinate
				.getLatitudinalDistance(secondCoordinate);
		double distanceSecondToFirst = secondCoordinate
				.getLatitudinalDistance(firstCoordinate);
		assertEquals(2.0, distanceFirstToSecond, DELTA);
		assertEquals(2.0, distanceSecondToFirst, DELTA);
	}

	@Test
	public void testLongitudinalDistance() {
		double distanceFirstToSecond = firstCoordinate
				.getLongitudinalDistance(secondCoordinate);
		double distanceSecondToFirst = secondCoordinate
				.getLongitudinalDistance(firstCoordinate);
		assertEquals(3.0, distanceFirstToSecond, DELTA);
		assertEquals(3.0, distanceSecondToFirst, DELTA);
	}

	@Test
	public void testCoordinateDistance() {
		Coordinate coordinateShouldBe = new Coordinate(2.0, 3.0);
		Coordinate coordinateDistanceFirstToSecond = firstCoordinate
				.getDistance(secondCoordinate);
		Coordinate coordinateDistanceSecondToFirst = secondCoordinate
				.getDistance(firstCoordinate);
		assertEquals(coordinateShouldBe, coordinateDistanceFirstToSecond);
		assertEquals(coordinateShouldBe, coordinateDistanceSecondToFirst);
	}

	@Test
	public void testDoubleDistance() {
		// Pythagoras theorem: sqrt(a^2 + b^2)
		double distanceShouldBe = 3.605551275;
		double doubleDistanceFirstToSecond = firstCoordinate
				.getDistanceAsDouble(secondCoordinate);
		double doubleDistanceSecondToFirst = secondCoordinate
				.getDistanceAsDouble(firstCoordinate);
		assertEquals(distanceShouldBe, doubleDistanceFirstToSecond, DELTA);
		assertEquals(distanceShouldBe, doubleDistanceSecondToFirst, DELTA);
	}

}
