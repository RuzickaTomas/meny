package cz.kurz.meny;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import cz.kurz.meny.dto.FrkExchange;
import cz.kurz.meny.service.ExchangeService;
import cz.kurz.meny.xml.Exchange;

@ExtendWith(value = MockitoExtension.class)
class ExchangeServiceTest {

	@Mock
	private RestTemplate template;
	
	private ExchangeService service;
	
	private String cnbUrl;
	
	private String frkUrl;
	
	@BeforeEach
	void setUp() {
		cnbUrl = "http://localhost:1234/cnb";
		frkUrl = "http://localhost:2345/frankfurt";
		service = new ExchangeService(template, cnbUrl, frkUrl);
	}
	
	@Test
	void testCnbExchangeParams() {
		service.getCNBExchange();
		ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Class<Exchange>> response = ArgumentCaptor.forClass(Class.class);
		verify(template).getForObject(url.capture(), response.capture());
		assertEquals(cnbUrl, url.getValue());
		assertEquals("Exchange", response.getValue().getSimpleName());
	}
	
	@Test
	void testNpeForCnbUrl() {
		ReflectionTestUtils.setField(service, "cnbUrl", null, String.class);
		assertNull(service.getCNBExchange());
	}
	
	@Test
	void testFrankExchangeParams() {
		assertDoesNotThrow(() -> service.getFrankExchange("CZK"));
		ArgumentCaptor<URI> url = ArgumentCaptor.forClass(URI.class);
		ArgumentCaptor<Class<FrkExchange>> response = ArgumentCaptor.forClass(Class.class);
		verify(template).getForObject(url.capture(), response.capture());
		frkUrl += "/latest?base=CZK&amount=1";
		assertEquals(frkUrl, url.getValue().toString());
		assertEquals("FrkExchange", response.getValue().getSimpleName());
	}
	
	@Test
	void testNpeForFrkUrl() {
		ReflectionTestUtils.setField(service, "frkUrl", null, String.class);
		FrkExchange result = assertDoesNotThrow(() -> service.getFrankExchange(null));
		assertNull(result);
	}
	
}
