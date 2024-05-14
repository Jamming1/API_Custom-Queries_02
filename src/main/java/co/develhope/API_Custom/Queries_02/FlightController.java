package co.develhope.API_Custom.Queries_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightRepository flightRepository;
    @GetMapping("/provision")
    public List<Flight> provisionFlights(@RequestParam(required = false, defaultValue = "100") int numFlights) {
        List<Flight> flights = new ArrayList<>();
        List<String> descriptions = Arrays.asList("Business Trip", "Vacation", "Family Visit", "Conference", "Holiday");
        List<String> airports = Arrays.asList("JFK", "LAX", "ORD", "ATL", "LHR", "CDG", "FRA", "DXB", "HND", "PEK");
        Random random = new Random();


        for(int i = 0; i < numFlights; i++) {
            FlightStatus flightStatus = FlightStatus.values()[random.nextInt(FlightStatus.values().length)];
            Flight flight = new Flight(descriptions.get(random.nextInt(descriptions.size())),
                    airports.get(random.nextInt(airports.size())),
                    airports.get(random.nextInt(airports.size())), flightStatus);

            flights.add(flight);
        }

        return flightRepository.saveAllAndFlush(flights);
    }

    @GetMapping("")
    public Page<Flight> getAllFlights(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fromAirport").ascending());
        return flightRepository.findAll(pageable);
    }

    @GetMapping("/onTime")
    public List<Flight> getOnTime() {
        List<Flight> flightList = flightRepository.findAll();
        return flightList.stream().filter(flight -> flight.getStatus() == FlightStatus.ON_TIME).toList();
    }

    @GetMapping("/byStatus")
    public List<Flight> getByStatuses(@RequestParam FlightStatus p1, @RequestParam FlightStatus p2) {
        return flightRepository.findByStatusIn(List.of(p1, p2));
    }

}