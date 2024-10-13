package cz.kurz.meny.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cz.kurz.meny.dto.FrkExchange;
import cz.kurz.meny.xml.Exchange;

@Component
public class ExchangeService {

	private String cnbUrl;

	private String frkUrl;

	private RestTemplate restTemplate;

	public ExchangeService(RestTemplate restTemplate, @Value("${exchange.cnb.url:}") String cnbUrl,
			@Value("${exchange.frk.url:}") String frkUrl) {
		this.restTemplate = restTemplate;
		this.cnbUrl = cnbUrl;
		this.frkUrl = frkUrl;
	}

	public Exchange getCNBExchange() {
		if (cnbUrl == null || cnbUrl.isBlank()) {
			return null;
		}
		return restTemplate.getForObject(cnbUrl, Exchange.class);
	}

	public FrkExchange getFrankExchange(String baseCurrencyCode) throws Exception {
		if (frkUrl == null || frkUrl.isBlank()) {
			return null;
		}
		if (baseCurrencyCode == null || baseCurrencyCode.isBlank()) {
			throw new IllegalArgumentException("Base currency is missing");
		}	
		UriComponents uriComp = UriComponentsBuilder.fromHttpUrl(frkUrl).path("/latest")
				.queryParam("base", baseCurrencyCode).queryParam("amount", 1).build();
		return restTemplate.getForObject(uriComp.toUri(), FrkExchange.class);
	}
}
