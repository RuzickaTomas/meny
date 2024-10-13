package cz.kurz.meny.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	public final static String CZ_DATE_FORMAT = "dd.MM.yyyy";
	
	@Override
	public Date unmarshal(String v) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DateAdapter.CZ_DATE_FORMAT);
		return format.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DateAdapter.CZ_DATE_FORMAT);
		return format.format(v);
	}

}
