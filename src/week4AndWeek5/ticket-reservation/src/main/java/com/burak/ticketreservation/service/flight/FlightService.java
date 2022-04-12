package com.burak.ticketreservation.service.flight;

import com.burak.ticketreservation.entity.*;
import com.burak.ticketreservation.requestDto.flightRequestDto.SaveFlightRequestDto;
import com.burak.ticketreservation.requestDto.flightRequestDto.UpdateFlightRequestDto;
import com.burak.ticketreservation.responseDto.PassengerResponseDto;

import java.util.List;

public interface FlightService {
    public List<Flight> findAllFlights();

    public List<THYFlight> findAllTHYFlights();
    public List<PegasusFlight> findAllPegasusFlights();
    public List<LufthansaFlight> findAllLufthansaFlights();

    public Flight findFlightById(int id);

    public String saveFlight(SaveFlightRequestDto saveFlightRequestDto);

    public Flight updateFlightTime(UpdateFlightRequestDto updateFlightRequestDto);

    public Flight deleteFlightById(int flightId);

    public List<PassengerResponseDto> getPassengersByFlightId(int flightId);
}
