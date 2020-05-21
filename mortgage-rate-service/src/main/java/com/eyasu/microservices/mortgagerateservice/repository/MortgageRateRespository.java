package com.eyasu.microservices.mortgagerateservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyasu.microservices.mortgagerateservice.model.MortgagePerState;

@Repository
public interface MortgageRateRespository extends JpaRepository<MortgagePerState, String> {
}
