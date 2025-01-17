package com.eyasu.microservices.mortgagerateservice.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eyasu.microservices.mortgagerateservice.exceptions.StateNotFoundException;
import com.eyasu.microservices.mortgagerateservice.model.MortgagePerState;
import com.eyasu.microservices.mortgagerateservice.repository.MortgageRateRespository;


@RestController
@RequestMapping("/mortgage-rates")
public class MortgageRateController {

	
	@Autowired
	private MortgageRateRespository repository;
		
	@GetMapping("")
	public CollectionModel<MortgagePerState> showAllStates() {
		List<MortgagePerState> rates = repository.findAll();
		
		Link link = linkTo(this.getClass()).withSelfRel();
		
		CollectionModel<MortgagePerState> model = CollectionModel.of(rates);
		
		for(MortgagePerState rate : rates) {
			Link selfLink = linkTo(methodOn(this.getClass()).
					showOneState(rate.getName())).withSelfRel();
			rate.add(selfLink);
		}
		
		model.add(link);
		
		return model;
	}
	
	
	@GetMapping("/state/{state}")
	public EntityModel<MortgagePerState> showOneState(@PathVariable String state) {
		
		Optional<MortgagePerState> rate = repository.findById(state);
		
		if(rate==null)
			throw new StateNotFoundException("state-"+state);
		
		Link link = linkTo(methodOn(this.getClass()).showAllStates()).withRel("all-rates");
		EntityModel<MortgagePerState> model = EntityModel.of(rate.get());
		model.add(link);
		
		return model;
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addState(@RequestBody MortgagePerState rate) {
		
		MortgagePerState rateSaved = repository.save(rate);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(rateSaved.getName()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/state/{state}")
	public ResponseEntity<Object> editState(@RequestBody MortgagePerState rate) {
				
		repository.save(rate);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping("/state/{state}")
	public void deleteState(@PathVariable String state) {
		
		MortgagePerState rate = repository.findById(state).get();
		
		if(rate==null)
			throw new StateNotFoundException("state-"+state);
		
		repository.deleteById(state);
	}
}
