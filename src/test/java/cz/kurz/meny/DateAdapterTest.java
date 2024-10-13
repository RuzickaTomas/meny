package cz.kurz.meny;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.kurz.meny.xml.DateAdapter;

class DateAdapterTest {

	DateAdapter adapter;
	
	@BeforeEach
	void setUp() {
		adapter = new DateAdapter();
	}
	
	@Test
	void testUnmarshallDateWithDash() {
		Date result = assertDoesNotThrow(() -> adapter.unmarshal("10.11.2024"));
		Instant in = LocalDate.of(2024, 11, 10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		assertEquals(Date.from(in), result);
	} 
	
	@Test
	void testMarshallDateWithDash() {
		Instant in = LocalDate.of(2024, 12, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		String result = assertDoesNotThrow(() -> adapter.marshal(Date.from(in)));
		assertEquals("12.12.2024", result);
	} 
	

	
}
