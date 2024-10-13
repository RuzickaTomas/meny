package cz.kurz.meny;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import cz.kurz.meny.xml.DecimalConverter;

class DecimalConverterTest {

	@Test
	void testDoubleWith5DecimalPlaces() {
		Double result = DecimalConverter.from(3.09999, "#.###");
		assertEquals(3.1, result);
	}
	
	@Test
	void testDoubleWith7DecimalPlacesAndPattern() {
		Double result = DecimalConverter.from(0.9999999, "###.###");
		assertEquals(1.0, result);
	}
	
	@Test
	void testDoubleWith7DecimalPlacesAndMisleadingPattern() {
		Double result = DecimalConverter.from(111.55667, "#.###");
		assertEquals(111.557, result);
	}
	
	@Test
	void testConvertingZero() {
		Double result = DecimalConverter.from(0.00001, "#.#");
		assertEquals(0.0, result);
	}
}
