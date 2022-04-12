package com.burak.ticketreservation.requestDto.flightRequestDto;


import com.burak.ticketreservation.validation.Airline;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaveFlightRequestDto {

    //Airline name must be thy, pegasus, or lufthansa. Else, it throws an exception
    @Airline
    private String airline;

    //Adding built-in data validation rules and error messages which is shown when an exception is thrown

    @NotEmpty(message = "fromCity field cannot be empty")
    private String fromCity;

    @NotEmpty(message = "toCity field cannot be empty")
    private String toCity;

    @NotNull
    @FutureOrPresent(message = "arrival date cannot be a past date")
    private LocalDateTime departureDate;

    @NotNull
    @FutureOrPresent(message = "arrival date cannot be a past date")
    private LocalDateTime arrivalDate;

    @Min(value = 10, message = "ticket price must be at least $10")
    private double baseTicketPrice;

    @Positive(message = "Seat capacity cannot be negative number")
    private int seatCapacity;


}
