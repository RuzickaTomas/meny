package cz.kurz.meny;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cz.kurz.meny.xml.Exchange;
import cz.kurz.meny.xml.ExchangeRow;
import cz.kurz.meny.xml.ExchangeTable;
import jakarta.xml.bind.JAXB;

class XmlConverterTest {
	
	@AfterEach
	void tearDown() {
		File file = new File("src/test/resources/testResult.xml");
		if (file.exists()) {
			file.delete();
		}
	}
	
	
	@Test
	void testXmlUnmarshal() {
		File file = new File("src/test/resources/testExchange.xml");
		Exchange result = JAXB.unmarshal(file, Exchange.class);
		assertNotNull(result);
		assertEquals("CNB", result.getBankName());
		assertNotNull(result.getTable());
		assertNotNull(result.getTable().getExchangeRows());
		assertTrue(result.getTable().getType().matches("[A-Z_]*"));
		ExchangeRow row = result.getTable().getExchangeRows().get(0);
		assertNotNull(row.getCode());
		assertNotNull(row.getCountry());
		assertNotNull(row.getAmount());
		assertNotNull(row.getCurrency());
	}
	
	
	@Test
	void testXmlMarshall() throws ParseException {
		ExchangeRow row = new ExchangeRow();
		row.setAmount("1");
		row.setCode("EUR");
		row.setCountry("EMU");
		row.setCurrency("euro");
		row.setExchangeRate(23.55);
		
		ExchangeTable table = new ExchangeTable();
		table.setExchangeRows(List.of(row));
		table.setType("KURZY_TRHU");
		
		Exchange root = new Exchange();
		root.setBankName("CNB");
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		root.setDate(format.parse("11.12.2024"));
		root.setSequence(132);
		root.setTable(table);

		File file = new File("src/test/resources/testResult.xml");
		JAXB.marshal(root, file);
		assertTrue(file.exists());
		assertTrue(file.getTotalSpace() > 0L);
	}
}
