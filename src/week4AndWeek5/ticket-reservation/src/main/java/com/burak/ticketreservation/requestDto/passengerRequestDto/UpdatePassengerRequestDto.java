package com.burak.ticketreservation.requestDto.passengerRequestDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class UpdatePassengerRequestDto {

    private int id;

    @Positive(message = "passenger age must be greater than 0")
    private int age;

}
