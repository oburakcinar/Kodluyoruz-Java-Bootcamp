package com.burak.ticketreservation.requestDto.passengerFlightDetailsRequestDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class SavePassengerFlightDetailsRequestDto {

    @PositiveOrZero(message = "flight id cannot be negative")
    private int flightId;

    @PositiveOrZero(message = "flight id cannot be negative")
    private int passengerId;

    @PositiveOrZero(message = "flight id cannot be negative")
    private int seatNumber;

    private boolean business;




}
