package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {

	private CartesianCoordinate cartesianErlangen;
	private CartesianCoordinate cartesianLosAngeles;

	private SphericCoordinate sphericLosAngeles;
	private SphericCoordinate sphericErlangen;

	private static final double distanceErlangenToLosAngelesInKm = 7712.928747957382;
	private static final double DELTA = 0.000001;

	@Before
	public void setUp() {
		cartesianErlangen = CartesianCoordinate.getInstance(4762.5137725,
				926.2823628, 4129.1772245);
		cartesianLosAngeles = CartesianCoordinate.getInstance(-1688.1891415,
				-3142.7037588, 5278.5482385);

		sphericErlangen = SphericCoordinate.getInstance(49.599937, 11.006300,
				SphericCoordinate.EARTH_RADIUS_IN_KM);
		sphericLosAngeles = SphericCoordinate.getInstance(34.052235,
				-118.243683, SphericCoordinate.EARTH_RADIUS_IN_KM);
	}

	// @Test
	// public void testDefaultConstructor() {
	// CartesianCoordinate coordinate = new CartesianCoordinate();
	// assertEquals(0.0, coordinate.getCartesianX(), DELTA);
	// assertEquals(0.0, coordinate.getCartesianY(), DELTA);
	// assertEquals(0.0, coordinate.getCartesianZ(), DELTA);
	// }

	@Test
	public void testXYZConstructor() {
		CartesianCoordinate coordinate = CartesianCoordinate.getInstance(
				7000.0, 250.0, 3.7);
		assertEquals(7000.0, coordinate.getCartesianX(), DELTA);
		assertEquals(250.0, coordinate.getCartesianY(), DELTA);
		assertEquals(3.7, coordinate.getCartesianZ(), DELTA);
	}

	@Test
	public void testCartesianDistance() {
		double distanceErlangenToLosAngeles = cartesianErlangen
				.getDistance(cartesianLosAngeles);
		double distanceLosAngelesToErlangen = cartesianLosAngeles
				.getDistance(cartesianErlangen);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceErlangenToLosAngeles, DELTA);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceLosAngelesToErlangen, DELTA);
	}

	@Test
	public void testCartesianConversion() {
		double distanceElangenCartesianToErlangenSpheric = cartesianErlangen
				.getDistance(sphericErlangen);
		double distanceErlangenSphericToErlangenCartesian = sphericErlangen
				.getDistance(cartesianErlangen);
		assertEquals(0.0, distanceElangenCartesianToErlangenSpheric, DELTA);
		assertEquals(0.0, distanceErlangenSphericToErlangenCartesian, DELTA);
	}

	@Test
	public void testDistanceWithConversionToCartesian() {
		double distanceErlangenCartesianToLosAngelesSpheric = cartesianErlangen
				.getDistance(sphericLosAngeles);
		double distanceLosAngelesCartesianToErlangenSpheric = cartesianLosAngeles
				.getDistance(sphericErlangen);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceErlangenCartesianToLosAngelesSpheric, DELTA);
		assertEquals(distanceErlangenToLosAngelesInKm,
				distanceLosAngelesCartesianToErlangenSpheric, DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetXWithNaN() {
		cartesianErlangen.setX(Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetYWithNaN() {
		cartesianErlangen.setX(Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetZWithNaN() {
		cartesianErlangen.setX(Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceWithNullAsParameter() {
		cartesianErlangen.getDistance(null);
	}

	@Test
	public void testEqualAndSameCartesianCoordinate() {
		CartesianCoordinate testSameCartesianErlangen = CartesianCoordinate
				.getInstance(4762.5137725, 926.2823628, 4129.1772245);
		assertTrue(testSameCartesianErlangen.isSame(cartesianErlangen));
		assertFalse(testSameCartesianErlangen.isSame(sphericErlangen));
		assertTrue(testSameCartesianErlangen.isEqual(sphericErlangen));
	}

	@Test
	public void testCoordinateInstancesField() {
		final int initialSizeOfCoordinateInstances = AbstractCoordinate.coordinateInstances
				.size();
		int coordinateInstancesSizeShouldValue = initialSizeOfCoordinateInstances;

		SphericCoordinate.getInstance(49.599937, 11.006300,
				SphericCoordinate.EARTH_RADIUS_IN_KM);
		CartesianCoordinate
				.getInstance(4762.5137725, 926.2823628, 4129.1772245);
		assertEquals(coordinateInstancesSizeShouldValue,
				AbstractCoordinate.coordinateInstances.size());

		SphericCoordinate.getInstance(20, 30, 40);
		coordinateInstancesSizeShouldValue++;
		assertEquals(coordinateInstancesSizeShouldValue,
				AbstractCoordinate.coordinateInstances.size());

		CartesianCoordinate.getInstance(50, 60, 70);
		coordinateInstancesSizeShouldValue++;
		assertEquals(coordinateInstancesSizeShouldValue,
				AbstractCoordinate.coordinateInstances.size());

		assertEquals(AbstractCoordinate.coordinateInstances.size(),
				initialSizeOfCoordinateInstances + 2);
	}

	@Test
	public void testCartesianCoordinateSetter() {
		final int initialSizeOfCoordinateInstances = AbstractCoordinate.coordinateInstances
				.size();
		int coordinateInstancesSizeShouldValue = initialSizeOfCoordinateInstances;
		CartesianCoordinate testCoordinate;

		testCoordinate = cartesianErlangen.setX(10);
		coordinateInstancesSizeShouldValue++;
		assertEquals(testCoordinate.getCartesianX(), 10.0, DELTA);
		assertEquals(coordinateInstancesSizeShouldValue,
				AbstractCoordinate.coordinateInstances.size());
		testCoordinate = cartesianErlangen.setY(10);
		coordinateInstancesSizeShouldValue++;
		assertEquals(testCoordinate.getCartesianY(), 10.0, DELTA);
		assertEquals(coordinateInstancesSizeShouldValue,
				AbstractCoordinate.coordinateInstances.size());
		testCoordinate = cartesianErlangen.setZ(10);
		coordinateInstancesSizeShouldValue++;
		assertEquals(testCoordinate.getCartesianZ(), 10.0, DELTA);
		assertEquals(coordinateInstancesSizeShouldValue,
				AbstractCoordinate.coordinateInstances.size());

		testCoordinate = cartesianErlangen.setX(10);
		testCoordinate = cartesianErlangen.setY(10);
		testCoordinate = cartesianErlangen.setZ(10);
		assertEquals(coordinateInstancesSizeShouldValue,
				AbstractCoordinate.coordinateInstances.size());

	}
}
