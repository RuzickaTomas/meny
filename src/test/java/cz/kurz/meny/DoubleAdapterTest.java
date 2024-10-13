package cz.kurz.meny;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.kurz.meny.xml.DoubleAdapter;

class DoubleAdapterTest {

	DoubleAdapter adapter;
	
	@BeforeEach
	void setUp() {
		adapter = new DoubleAdapter();
	}
	
	@Test
	void testUnmarshallDoubleWithDash() {
		Double result = assertDoesNotThrow(() -> adapter.unmarshal("123,344"));
		assertEquals(123.344, result);
	} 
	
	@Test
	void testMarshallDoubleWithDash() {
		String result = assertDoesNotThrow(() -> adapter.marshal(123.344));
		assertEquals("123,344", result);
	} 
	
	@Test
	void testMmarshallDoubleWithDashNpeException() {
		assertThrows(NullPointerException.class,() -> adapter.unmarshal(null));
	} 

	@Test
	void testMmarshallDoubleWithDashNumberException() {
		assertThrows(NumberFormatException.class,() -> adapter.unmarshal("ABCD,EF"));
	} 
	
}
