package com.alibahadirsensoz.flightsearchapi;

import com.alibahadirsensoz.flightsearchapi.model.Airport;
import com.alibahadirsensoz.flightsearchapi.repository.AirportRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FlightSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApiApplication.class, args);
	}



	@Autowired
	AirportRepository airportRepository;

	@PostConstruct
	public void populateData() {

		Airport airport1 = new Airport();
		airport1.setCity("Istanbul");



		airportRepository.save(airport1);
	}
}
