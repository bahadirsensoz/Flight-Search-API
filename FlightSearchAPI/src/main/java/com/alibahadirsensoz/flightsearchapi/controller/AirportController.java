package com.alibahadirsensoz.flightsearchapi.controller;


import com.alibahadirsensoz.flightsearchapi.model.Airport;
import com.alibahadirsensoz.flightsearchapi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/airports")
public class AirportController {


    @Autowired
    private AirportService airportService;

    @GetMapping("/")
    public List<Airport> getAirports(){
        return airportService.getAirports();
    }

    @GetMapping("/{airportId}")
    public Optional<Airport> getAirportByAirportId(@PathVariable String airportId){
        return airportService.getAirportByAirportId(airportId);

    }

    @PostMapping("/addairport")
    public Airport addAirport(@RequestBody Airport airport) {
        return airportService.addAirport(airport);
    }

    @PutMapping("/{airportId}")
    public Airport updateAirport(@PathVariable String airportId, @RequestBody Airport airport){
        return  airportService.updateAirport(airportId, airport);
    }

    @DeleteMapping("/{airportId}")
    public void deleteAirport(@PathVariable String airportId){
        airportService.deleteAirport(airportId);
    }


}
