package com.alibahadirsensoz.flightsearchapi;

import com.alibahadirsensoz.flightsearchapi.api.model.Flight;
import com.alibahadirsensoz.flightsearchapi.repository.FlightRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class FlightSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApiApplication.class, args);
	}


	@Autowired
	FlightRepository flightRepository;

	@PostConstruct
	public void populateData() {
		Flight flight1 = new Flight();
		flight1.setPrice(5000);
		flight1.setDepartureAirport("Sabiha Gokcen");
		flight1.setLandingAirport("Antalya Airport");
		flight1.setDepartureTime(LocalDateTime.now());
		flight1.setLandingTime(LocalDateTime.now().plusHours(2));

		Flight flight2 = new Flight();
		flight2.setPrice(3000);
		flight2.setDepartureAirport("Sabiha Gokcen");
		flight2.setLandingAirport("İzmir Airport");
		flight2.setDepartureTime(LocalDateTime.now());
		flight2.setLandingTime(LocalDateTime.now().plusHours(1));


		flightRepository.save(flight1);
		flightRepository.save(flight2);
	}
}
