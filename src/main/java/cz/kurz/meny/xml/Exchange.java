package cz.kurz.meny.xml;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "kurzy")
@XmlAccessorType(XmlAccessType.FIELD)
public class Exchange implements Serializable {

	@XmlAttribute(name = "banka")
	private String bankName;
	
	@XmlAttribute(name = "datum")
	@XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
	private Date date;
	
	@XmlAttribute(name = "poradi")
	private Integer sequence;
	
	@XmlElement(name = "tabulka")
	private ExchangeTable table;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public ExchangeTable getTable() {
		return table;
	}

	public void setTable(ExchangeTable table) {
		this.table = table;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankName, date, sequence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exchange other = (Exchange) obj;
		return Objects.equals(bankName, other.bankName) && Objects.equals(date, other.date)
				&& Objects.equals(sequence, other.sequence);
	}
	
	
	
}
