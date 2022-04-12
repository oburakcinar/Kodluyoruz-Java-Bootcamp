package com.burak.ticketreservation.responseDto;

import com.burak.ticketreservation.entity.Passenger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FlightResponseDto {

    private int flightId;

    private String fromCity;

    private String toCity;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private double ticketPrice;

    private boolean isBusiness;
}
