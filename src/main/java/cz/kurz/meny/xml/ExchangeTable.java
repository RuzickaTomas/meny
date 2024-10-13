package cz.kurz.meny.xml;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tabulka")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeTable implements Serializable {
	
	@XmlAttribute(name = "typ")
	private String type;

	@XmlElement(name = "radek")
	private List<ExchangeRow> exchangeRows;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ExchangeRow> getExchangeRows() {
		return exchangeRows;
	}

	public void setExchangeRows(List<ExchangeRow> exchangeRows) {
		this.exchangeRows = exchangeRows;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exchangeRows, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExchangeTable other = (ExchangeTable) obj;
		return Objects.equals(exchangeRows, other.exchangeRows) && Objects.equals(type, other.type);
	}
	
	
}
