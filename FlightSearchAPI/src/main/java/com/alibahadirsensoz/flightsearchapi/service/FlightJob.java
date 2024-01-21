package com.alibahadirsensoz.flightsearchapi.service;


import com.alibahadirsensoz.flightsearchapi.model.Flight;
import com.alibahadirsensoz.flightsearchapi.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FlightJob {

    @Autowired
    FlightService flightService;

    Logger log = LoggerFactory.getLogger(FlightJob.class);

    List<String> cities = Arrays.asList("Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
        "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "İçel (Mersin)", "İstanbul",
        "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize",
        "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman",
        "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce");

    private static String getRandomElement(List<String> list) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(list.size());
        return list.get(randomIndex);
    }

    @Scheduled(fixedDelay = 86400000)
    public void AddFlight() throws InterruptedException {
        String randomcity1 = getRandomElement(cities);
        String randomcity2 = getRandomElement(cities);
        Flight flight = new Flight();
        flight.setDepartureAirport(randomcity1);
        flight.setLandingAirport(randomcity2);
        flight.setPrice(new Random().nextInt(5000));
        flight.setDepartureTime(LocalDateTime.now());
        flight.setLandingTime(LocalDateTime.now().plusHours(2));
        flight = flightService.addFlight(flight);
        log.info("Added new Flight", flight);
    }

    @Scheduled(cron = "0 8 * * * *")
    public void NewFlights() {
        String randomcity1 = getRandomElement(cities);
        String randomcity2 = getRandomElement(cities);
        Flight flight = new Flight();
        flight.setDepartureAirport(randomcity1);
        flight.setLandingAirport(randomcity2);
        flight.setPrice(new Random().nextInt(5000));
        flight.setDepartureTime(LocalDateTime.now());
        flight.setLandingTime(LocalDateTime.now().plusHours(2));
        flight = flightService.addFlight(flight);
        log.info("Added new Flight", flight);
    }




}
