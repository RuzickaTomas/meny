package cz.kurz.meny;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("JUnit Platform Suite")
@SelectClasses({ DecimalConverterTest.class, DateAdapterTest.class, DoubleAdapterTest.class,
		ExchangeControllerTest.class, ExchangeServiceTest.class, XmlConverterTest.class })
class MenyApplicationTests {
}
