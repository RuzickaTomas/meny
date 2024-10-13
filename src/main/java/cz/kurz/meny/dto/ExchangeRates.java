package cz.kurz.meny.dto;

import java.io.Serializable;
import java.util.Objects;

public final class ExchangeRates implements Serializable { 

	private final String currencyCode;
	private final Integer amount;
	//name of service or bank that we take as a base
	private final String baseServiceName;
	private final Double baseRate;
	private final String comparingServiceName;
	private final Double comparingRate;
	//Difference between baseRate and comparingRate
	private final Double bcDiff;
	
	
	public static class Builder {
	
		private String currencyCode;
		private Integer amount;
		//name of service or bank that we take as a base
		private String baseServiceName;
		private Double baseRate;
		private String comparingServiceName;
		private Double comparingRate;
		//Difference between baseRate and comparingRate
		private Double bcDiff;
		

	public Builder currencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		return this;
	}

	public Builder amount(Integer amount) {
		this.amount = amount;
		return this;
	}

	public Builder baseServiceName(String baseServiceName) {
		this.baseServiceName = baseServiceName;
		return this;
	}

	public Builder baseRate(Double baseRate) {
		this.baseRate = baseRate;
		return this;
	}

	public Builder comparingServiceName(String comparingServiceName) {
		this.comparingServiceName = comparingServiceName;
		return this;
	}

	public Builder comparingRate(Double comparingRate) {
		this.comparingRate = comparingRate;
		return this;
	}

	public Builder bcDiff(Double bcDiff) {
		this.bcDiff = bcDiff;
		return this;
	}
	
	public ExchangeRates build() {
		return new ExchangeRates(this);
	}
	}
	private ExchangeRates(Builder builder) {
		this.currencyCode = builder.currencyCode;
		this.amount = builder.amount;
		this.baseServiceName = builder.baseServiceName;
		this.baseRate = builder.baseRate;
		this.comparingServiceName = builder.comparingServiceName;
		this.comparingRate = builder.comparingRate;
		this.bcDiff = builder.bcDiff;
	}
	
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public Integer getAmount() {
		return amount;
	}
	public String getBaseServiceName() {
		return baseServiceName;
	}
	public Double getBaseRate() {
		return baseRate;
	}
	public String getComparingServiceName() {
		return comparingServiceName;
	}
	public Double getComparingRate() {
		return comparingRate;
	}
	public Double getBcDiff() {
		return bcDiff;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, baseRate, baseServiceName, bcDiff, comparingRate, comparingServiceName,
				currencyCode);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExchangeRates other = (ExchangeRates) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(baseRate, other.baseRate)
				&& Objects.equals(baseServiceName, other.baseServiceName) && Objects.equals(bcDiff, other.bcDiff)
				&& Objects.equals(comparingRate, other.comparingRate)
				&& Objects.equals(comparingServiceName, other.comparingServiceName)
				&& Objects.equals(currencyCode, other.currencyCode);
	}


	@Override
	public String toString() {
		return "ExchangeRates [currencyCode=" + currencyCode + ", amount=" + amount + ", baseServiceName="
				+ baseServiceName + ", baseRate=" + baseRate + ", comparingServiceName=" + comparingServiceName
				+ ", comparingRate=" + comparingRate + ", bcDiff=" + bcDiff + "]";
	}
	
	
	
	
	
}
