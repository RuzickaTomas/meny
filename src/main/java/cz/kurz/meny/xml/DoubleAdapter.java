package cz.kurz.meny.xml;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DoubleAdapter extends XmlAdapter<String, Double> {

	@Override
	public Double unmarshal(String v) throws Exception {
		return Double.valueOf(v.replace(',', '.'));
	}

	@Override
	public String marshal(Double v) throws Exception {
		return v.toString().replace('.', ',');
	}

}
