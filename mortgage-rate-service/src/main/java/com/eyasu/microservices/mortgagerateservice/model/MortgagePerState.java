package com.eyasu.microservices.mortgagerateservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

@Entity
public class MortgagePerState extends RepresentationModel<MortgagePerState>{

	@Id
	@Column(name="name")
	private String state;
	private String averageRate15Years;
	private String averageRate30Years;
	private String averageInterestRate;

	
	
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


	
	public MortgagePerState() {}
	
	public MortgagePerState(String state, String averageRate15Years, String averageRate30Years) {
		super();
		this.state = state;
		this.averageRate15Years = averageRate15Years;
		this.averageRate30Years = averageRate30Years;
	}

	public String getName() {
		return state;
	}

	public void setName(String name) {
		this.state = name;
	}

	public String getAverageRate15Years() {
		return averageRate15Years;
	}

	public void setAverageRate15Years(String averageRate15Years) {
		this.averageRate15Years = averageRate15Years;
	}

	public String getAverageRate30Years() {
		return averageRate30Years;
	}

	public void setAverageRate30Years(String averageRate30Years) {
		this.averageRate30Years = averageRate30Years;
	}
	

	

}
