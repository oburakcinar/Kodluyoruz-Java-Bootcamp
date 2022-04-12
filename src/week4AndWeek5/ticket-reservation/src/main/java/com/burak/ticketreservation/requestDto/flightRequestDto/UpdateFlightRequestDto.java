package com.burak.ticketreservation.requestDto.flightRequestDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateFlightRequestDto {

    private int id;

    @FutureOrPresent(message = "departure date cannot be a past date")
    private LocalDateTime departureDate;

    @FutureOrPresent(message = "arrival date cannot be a past date")
    private LocalDateTime arrivalDate;

}
