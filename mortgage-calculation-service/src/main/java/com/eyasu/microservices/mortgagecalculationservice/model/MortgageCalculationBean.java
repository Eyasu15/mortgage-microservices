package com.eyasu.microservices.mortgagecalculationservice.model;

import java.math.BigDecimal;

public class MortgageCalculationBean {
	
	private String state;
	private String averageInterestRate;
	private Integer period;
	private Integer principle;
	private BigDecimal monthlyPayment;
	
	public MortgageCalculationBean() {}
	
	public MortgageCalculationBean(String state, String averageInterestRate, Integer period, Integer principle, BigDecimal monthlyPayment) {
		super();
		this.state = state;
		this.averageInterestRate = averageInterestRate;
		this.period = period;
		this.principle = principle;
		this.monthlyPayment = monthlyPayment;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAverageInterestRate() {
		return averageInterestRate;
	}

	public void setAverageInterestRate(String averageInterestRate) {
		this.averageInterestRate = averageInterestRate;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public BigDecimal getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(BigDecimal monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public Integer getPrinciple() {
		return principle;
	}

	public void setPrinciple(Integer principle) {
		this.principle = principle;
	}


}
