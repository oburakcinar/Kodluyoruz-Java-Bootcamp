package com.burak.ticketreservation.service.passenger;

import com.burak.ticketreservation.entity.Passenger;
import com.burak.ticketreservation.requestDto.passengerRequestDto.SavePassengerRequestDto;
import com.burak.ticketreservation.requestDto.passengerRequestDto.UpdatePassengerRequestDto;
import com.burak.ticketreservation.responseDto.PassengerResponseDto;

import java.util.List;

public interface PassengerService {

    public List<Passenger> getPassengers();
    public Passenger getPassengerById(int id);
    public String savePassenger(SavePassengerRequestDto savePassengerRequestDto);
    public void save(Passenger passenger);

    public Passenger updatePassenger(UpdatePassengerRequestDto updatePassengerRequestDto);

    public PassengerResponseDto deletePassengerById(int passengerId);
}
