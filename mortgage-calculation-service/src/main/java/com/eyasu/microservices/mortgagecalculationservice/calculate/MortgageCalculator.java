package com.eyasu.microservices.mortgagecalculationservice.calculate;

import java.math.BigDecimal;

public class MortgageCalculator {
	  
	private BigDecimal annualInterest;
	private Integer period;
	private Integer principle;

	

    public MortgageCalculator(Integer principle,BigDecimal annualInterest,Integer period) {
		
    	this.principle = principle;
		this.annualInterest = annualInterest;
		this.period = period;
	}

	public BigDecimal calculateMortgage() {
		
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayments = getNumberOfPayments();

        return BigDecimal.valueOf(principle * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1));
    }

    private int getNumberOfPayments() {
        return period * 12;
    }

    private float getMonthlyInterest() {
        return annualInterest.floatValue() / 100 / 12;
    }
}