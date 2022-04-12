package com.burak.ticketreservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "airlines_name")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    @JsonIgnore //passengers field will not be converted to JSON automatically
    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="flight_passenger_details",
            joinColumns = @JoinColumn(name="flight_id"),
            inverseJoinColumns = @JoinColumn(name="passenger_id"))
    private List<Passenger> passengers;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime departureDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime arrivalDate;

    @JsonIgnore //seats field will not be converted to JSON automatically
    @ElementCollection
    private List<Boolean> seats;

    @Column(name = "base_ticket_price")
    private double baseTicketPrice;

    //this method is overwritten in child classes
    public double calculateTicketPrice(Passenger passenger, PassengerFlightDetails passengerFlightDetails) {
        return baseTicketPrice;
    }

}
