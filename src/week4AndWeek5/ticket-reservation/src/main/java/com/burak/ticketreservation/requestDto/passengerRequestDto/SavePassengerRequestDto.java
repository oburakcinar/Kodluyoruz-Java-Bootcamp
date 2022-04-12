package com.burak.ticketreservation.requestDto.passengerRequestDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SavePassengerRequestDto {

    @NotEmpty(message = "passenger name cannot be blank")
    private String name;

    @Positive(message = "passenger age must be greater than 0")
    private int age;


}
