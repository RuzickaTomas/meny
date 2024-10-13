package cz.kurz.meny;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.kurz.meny.dto.ExchangeRates;
import cz.kurz.meny.dto.FrkExchange;
import cz.kurz.meny.rest.ExchangeController;
import cz.kurz.meny.service.ExchangeService;
import cz.kurz.meny.xml.Exchange;
import jakarta.xml.bind.JAXB;

@ExtendWith(MockitoExtension.class)
class ExchangeControllerTest {
	
	private ExchangeController controller;
	
	@Mock
	private ExchangeService service;
	
	@BeforeEach
	void setUp() {
		controller = new ExchangeController(service);
	}
	
	@Test
	void testAggregatedResults() {
		File fileXml = new File("src/test/resources/testExchange.xml");
		Exchange resultXml = JAXB.unmarshal(fileXml, Exchange.class);
		assertNotNull(resultXml);
		File fileJson = new File("src/test/resources/testFrkExchange.json");
		assertNotNull(fileJson);
		ObjectMapper mapper = new ObjectMapper();
		FrkExchange resultJson = assertDoesNotThrow(() -> mapper.readValue(fileJson, FrkExchange.class));
		
		when(service.getCNBExchange()).thenReturn(resultXml);
		when(assertDoesNotThrow(() -> service.getFrankExchange(anyString()))).thenReturn(resultJson);
		
		List<ExchangeRates> rates = assertDoesNotThrow(() -> controller.exchangeRates());
		assertNotNull(rates);
		assertTrue(!rates.isEmpty());
		rates.forEach(rate -> {
			assertEquals("CNB", rate.getBaseServiceName());
			assertEquals("Frankfurter", rate.getComparingServiceName());
			assertTrue(rate.getAmount() >= 1);
			assertTrue(rate.getBcDiff() <= 5);
		});
	}
}
