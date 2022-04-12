package com.burak.ticketreservation.converter;

import com.burak.ticketreservation.entity.Passenger;
import com.burak.ticketreservation.requestDto.passengerRequestDto.SavePassengerRequestDto;
import com.burak.ticketreservation.responseDto.PassengerResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerConverter {

    public Passenger convertPassengerDtoToPassenger(SavePassengerRequestDto savePassengerRequestDto) {
        //transferring savePassengerRequestDto data to newly created passenger object
        Passenger passenger = new Passenger();
        passenger.setName(savePassengerRequestDto.getName());
        passenger.setAge(savePassengerRequestDto.getAge());
        passenger.setFlights(new ArrayList<>()); //flight list is empty since it is just created

        return passenger;
    }

    //crating passengerresponseDto
    public PassengerResponseDto convertPassengerToPassengerResponseDto(Passenger passenger) {
        PassengerResponseDto passengerResponseDto = new PassengerResponseDto();
        passengerResponseDto.setId(passenger.getId());
        passengerResponseDto.setName(passenger.getName());
        passengerResponseDto.setAge(passenger.getAge());

        return passengerResponseDto;
    }

    //using the converting method below in order to deal with list
    public List<PassengerResponseDto> convertPassengerListToPassengerResponseDtoList(List<Passenger> passengers) {
        List<PassengerResponseDto> passengerResponseDtoList = new ArrayList<>();
        for (Passenger passenger : passengers) {
            passengerResponseDtoList.add(convertPassengerToPassengerResponseDto(passenger));
        }
        return passengerResponseDtoList;
    }
}
