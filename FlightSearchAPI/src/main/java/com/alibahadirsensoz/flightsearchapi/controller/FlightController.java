package com.alibahadirsensoz.flightsearchapi.controller;


import com.alibahadirsensoz.flightsearchapi.model.Flight;
import com.alibahadirsensoz.flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/flights")
public class FlightController {


    @Autowired
    private FlightService flightService;

    @GetMapping("/")
    public List<Flight> getFlights(){
        return flightService.getFlights();
    }

    @GetMapping("/{flightId}")
    public Optional<Flight> getFlightByFlightId(@PathVariable String flightId){
        return flightService.getFlightByFlightId(flightId);

    }

    @PostMapping("/addflight")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping("/{flightId}")
    public Flight updateFlight(@PathVariable String flightId, @RequestBody Flight flight){
        return  flightService.updateFlight(flightId, flight);
    }

    @DeleteMapping("/{flightId}")
    public void deleteFlight(@PathVariable String flightId){
        flightService.deleteFlight(flightId);
    }


}
