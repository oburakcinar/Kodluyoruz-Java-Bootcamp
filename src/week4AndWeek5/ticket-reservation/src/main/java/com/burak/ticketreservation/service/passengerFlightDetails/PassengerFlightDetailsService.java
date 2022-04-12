package com.burak.ticketreservation.service.passengerFlightDetails;

import com.burak.ticketreservation.entity.PassengerFlightDetails;
import com.burak.ticketreservation.requestDto.passengerFlightDetailsRequestDto.SavePassengerFlightDetailsRequestDto;
import com.burak.ticketreservation.responseDto.FlightResponseDto;

import java.util.List;

public interface PassengerFlightDetailsService {
    public String savePassengerFlightDetail(SavePassengerFlightDetailsRequestDto savePassengerFlightDetailsRequestDto);
    public String deletePassengerFlightDetail(int passengerFlightDetailId);
    public PassengerFlightDetails findById(int passengerFlightDetailsId);
    public PassengerFlightDetails findByFlightIdAndPassengerId(int flightId, int passengerId);
    public List<FlightResponseDto> findFlightsByPassengerId(int passengerId);
}
