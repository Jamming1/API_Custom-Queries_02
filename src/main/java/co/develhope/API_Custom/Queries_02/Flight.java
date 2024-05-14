package co.develhope.API_Custom.Queries_02;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String fromAirport;
    private String toAirport;
    private FlightStatus status;



    public Flight(String description, String fromAirport, String toAirport, FlightStatus flightStatus) {
        this.description = description;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.status = flightStatus;
    }
}