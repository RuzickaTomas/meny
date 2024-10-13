package cz.kurz.meny.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.kurz.meny.dto.ExchangeRates;
import cz.kurz.meny.dto.FrkExchange;
import cz.kurz.meny.service.ExchangeService;
import cz.kurz.meny.xml.DecimalConverter;
import cz.kurz.meny.xml.Exchange;
import cz.kurz.meny.xml.ExchangeRow;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	private ExchangeService service;
	
	public ExchangeController(ExchangeService service) {
		this.service = service;
	}
	/**
	 * Calls two services for currency exchange rate and will
	 * return aggregated object containing difference between rates 
	 * of each service
	 * @return List
	 */
	@GetMapping
	public List<ExchangeRates> exchangeRates() throws Exception {
		log.debug("Start");
		long start = System.currentTimeMillis();
		Exchange exchange = service.getCNBExchange();
		log.debug("cnb took: {}", (System.currentTimeMillis() - start));
		long frkStart = System.currentTimeMillis();
		FrkExchange frkExchange = service.getFrankExchange("CZK");
		log.debug("frank took: {}", (System.currentTimeMillis() - frkStart));
		List<ExchangeRow> rows = exchange.getTable().getExchangeRows();
		List<ExchangeRates> list= new ArrayList<>();
		for (ExchangeRow row : rows) {
			if ("XDR".equals(row.getCode())) { //excluded due to XDR is not a currency but bulk of 5 currencies
				continue;
			}
			Float f = frkExchange.rates().get(row.getCode());
			Float frkRate = Float.valueOf(row.getAmount())/f;
			Double dFrkRate = DecimalConverter.from(frkRate.doubleValue(), "###.###");
			Double ratesDiff = DecimalConverter.from(( row.getExchangeRate() - dFrkRate ), "###.####");
			
			ExchangeRates.Builder builder = new ExchangeRates.Builder();
			ExchangeRates tempRates = builder.currencyCode(row.getCode())
				   .amount(Integer.valueOf(row.getAmount()))
				   .baseServiceName("CNB")
				   .baseRate(row.getExchangeRate())
				   .comparingServiceName("Frankfurter")
				   .comparingRate(dFrkRate)
				   .bcDiff(ratesDiff)
				   .build();
			list.add(tempRates); 

		}
		log.debug((System.currentTimeMillis() - start)+"");
		log.debug("END");
		return list;
	}
}
