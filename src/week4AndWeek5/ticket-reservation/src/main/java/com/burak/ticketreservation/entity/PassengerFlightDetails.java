package com.burak.ticketreservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

//this class stores the flight details (seat number, purchase time etc.) which will be needed when passenger wants to return the ticket
@Table
@Entity(name = "flight_passenger_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerFlightDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="flight_id")
    private int flightId;

    @Column(name="passenger_id")
    private int passengerId;

    @Column(name = "ticket_price")
    private double ticketPrice;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "is_business")
    private boolean business;


}
