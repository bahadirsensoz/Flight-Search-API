package com.alibahadirsensoz.flightsearchapi.repository;

import com.alibahadirsensoz.flightsearchapi.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface SearchRepository extends MongoRepository<Flight, String> {

    List<Flight> findFlightsByDepartureAirportAndLandingAirportAndDepartureTime(String departureAirport, String landingAirport, LocalDateTime departureTime);

    List<Flight> findFlightsByDepartureAirportAndLandingAirportAndDepartureTimeAndLandingTime(String departureAirport, String landingAirport, LocalDateTime departureTime, LocalDateTime landingTime);

    List<Flight> findFlightsByDepartureAirport(String departureAirport);

    List<Flight> findFlightsByDepartureTimeAfter(LocalDateTime departureTime);

    List<Flight> findFlightsByDepartureTimeBefore(LocalDateTime departureTime);

    @Query("{ 'departureTime': { '$gte': ?0, '$lte': ?1 } }")
    List<Flight> findFlightsByDepartureTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<Flight> findByDepartureTimeBetween(LocalDateTime requestTime, LocalDateTime departureTime);

    List<Flight> findFlightsByLandingAirport(String landingAirport);

//    List<Flight> findByDepartureAirportAndLandingAirportAndDepartureTime(
//            String departureAirport, String landingAirport, LocalDateTime departureTime);
//
//    List<Flight> findByDepartureAirportAndLandingAirportAndDepartureTimeGreaterThanEqualAndLandingTimeLessThanEqual(
//            String departureAirport, String landingAirport, LocalDateTime departureTime, LocalDateTime landingTime);
}
