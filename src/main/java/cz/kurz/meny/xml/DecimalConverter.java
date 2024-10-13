package cz.kurz.meny.xml;

import java.text.DecimalFormat;


public class DecimalConverter {

	public static Double from(Double d, String pattern) {
		DecimalFormat format = new DecimalFormat(pattern);
		String formatted = format.format(d);
		return Double.valueOf(formatted);
	}
}
