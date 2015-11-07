package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {

	private CartesianCoordinate cartesianErlangen;
	private CartesianCoordinate cartesianLosAngeles;

	private SphericCoordinate sphericLosAngeles;
	private SphericCoordinate sphericErlangen;

	// other distance than spheric --> no curvature of the earth
	private static final double cartesianDistanceErlangenToLosAngelesInKm = 7712.928747957382;
	private static final double DELTA = 0.000001;

	@Before
	public void setUp() {
		cartesianErlangen = new CartesianCoordinate(4762.5137725, 926.2823628,
				4129.1772245);
		cartesianLosAngeles = new CartesianCoordinate(-1688.1891415,
				-3142.7037588, 5278.5482385);

		sphericErlangen = new SphericCoordinate(49.599937, 11.006300);
		sphericLosAngeles = new SphericCoordinate(34.052235, -118.243683);
	}

	@Test
	public void testDefaultConstructor() {
		CartesianCoordinate coordinate = new CartesianCoordinate();
		assertEquals(0.0, coordinate.getX(), DELTA);
		assertEquals(0.0, coordinate.getY(), DELTA);
		assertEquals(0.0, coordinate.getZ(), DELTA);
	}

	@Test
	public void testXYZConstructor() {
		CartesianCoordinate coordinate = new CartesianCoordinate(7000.0, 250.0,
				3.7);
		assertEquals(7000.0, coordinate.getX(), DELTA);
		assertEquals(250.0, coordinate.getY(), DELTA);
		assertEquals(3.7, coordinate.getZ(), DELTA);
	}

	@Test
	public void testCartesianDistance() {
		double distanceErlangenToLosAngeles = cartesianErlangen
				.getDistance(cartesianLosAngeles);
		double distanceLosAngelesToErlangen = cartesianLosAngeles
				.getDistance(cartesianErlangen);
		assertEquals(cartesianDistanceErlangenToLosAngelesInKm,
				distanceErlangenToLosAngeles, DELTA);
		assertEquals(cartesianDistanceErlangenToLosAngelesInKm,
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
		assertEquals(cartesianDistanceErlangenToLosAngelesInKm,
				distanceErlangenCartesianToLosAngelesSpheric, DELTA);
		assertEquals(cartesianDistanceErlangenToLosAngelesInKm,
				distanceLosAngelesCartesianToErlangenSpheric, DELTA);
	}

}
