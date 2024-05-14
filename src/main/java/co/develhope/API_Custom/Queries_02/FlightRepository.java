package co.develhope.API_Custom.Queries_02;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByStatusIn(List<FlightStatus> status);
}
