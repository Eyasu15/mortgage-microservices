package com.eyasu.microservices.mortgagecalculationservice.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eyasu.microservices.mortgagecalculationservice.calculate.MortgageCalculator;
import com.eyasu.microservices.mortgagecalculationservice.model.MortgageCalculationBean;

@RestController
@RequestMapping("/mortgage-calculation")
public class MortgageCalculationController {	
	
	@GetMapping("/state/{state}"
			+ "/period/{period}"
			+ "/principle/{principle}")
	public MortgageCalculationBean retrieveMortgage(@PathVariable String state,
		@PathVariable Integer period, @PathVariable Integer principle) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("state", state);
		
		
		ResponseEntity<MortgageCalculationBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/mortgage-rates/state/{state}",
															MortgageCalculationBean.class, uriVariables);
		
		MortgageCalculationBean response = responseEntity.getBody();
		
		response.setPeriod(period);
		response.setPrinciple(principle);
		
		String rate = response.getAverageInterestRate().substring(0, 3);
		
		BigDecimal monthlyPayment = new MortgageCalculator(principle,new BigDecimal(rate), period).calculateMortgage();
		DecimalFormat df = new DecimalFormat("0.00");
		
		response.setMonthlyPayment(new BigDecimal(df.format(monthlyPayment)));
		
		return response;
	}
}
