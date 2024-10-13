package cz.kurz.meny.xml;

import java.io.Serializable;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "radek")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRow implements Serializable {
	
	@XmlAttribute(name = "kod")
	private String code;
	
	@XmlAttribute(name = "mena")
	private String currency;
	
	@XmlAttribute(name = "mnozstvi")
	private String amount;
	
	@XmlAttribute(name = "kurz")
	@XmlJavaTypeAdapter(value = DoubleAdapter.class, type = Double.class)
	private Double exchangeRate;
	
	@XmlAttribute(name = "zeme")
	private String country;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, code, country, currency, exchangeRate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExchangeRow other = (ExchangeRow) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(code, other.code)
				&& Objects.equals(country, other.country) && Objects.equals(currency, other.currency)
				&& Objects.equals(exchangeRate, other.exchangeRate);
	}
	
	
	
}
