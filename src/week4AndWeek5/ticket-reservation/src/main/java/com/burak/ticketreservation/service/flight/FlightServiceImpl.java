package com.burak.ticketreservation.service.flight;

import com.burak.ticketreservation.converter.FlightConverter;
import com.burak.ticketreservation.converter.PassengerConverter;
import com.burak.ticketreservation.dao.FlightRepository;
import com.burak.ticketreservation.entity.*;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.exception.EntityNotFoundException;
import com.burak.ticketreservation.requestDto.flightRequestDto.SaveFlightRequestDto;
import com.burak.ticketreservation.requestDto.flightRequestDto.UpdateFlightRequestDto;
import com.burak.ticketreservation.responseDto.PassengerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository<Flight> flightRepo;

    private final FlightRepository<THYFlight> thyFlightRepo;

    private final FlightRepository<PegasusFlight> pegasusFlightRepo;

    private final FlightRepository<LufthansaFlight> lufthansaFlightRepo;

    private final FlightConverter flightConverter;

    private final PassengerConverter passengerConverter;

    @Override
    public List<Flight> findAllFlights() {
        return flightRepo.findAll();
    }

    @Override
    public List<THYFlight> findAllTHYFlights() {
        return thyFlightRepo.findAllTHYFlights();
    }

    @Override
    public List<PegasusFlight> findAllPegasusFlights() {
        return pegasusFlightRepo.findAllPegasusFlights();
    }

    @Override
    public List<LufthansaFlight> findAllLufthansaFlights() {
        return lufthansaFlightRepo.findAllLufthansaFlights();
    }

    @Override
    public Flight findFlightById(int id) {
        Optional<Flight> flight = flightRepo.findById(id);
        if (flight.isPresent()) {
            return flight.get();
        } else {
            //if flight object is null, throw exception
            throw new EntityNotFoundException("Flight id not found - " + id);
        }
    }

    @Override
    public String saveFlight(SaveFlightRequestDto saveFlightRequestDto) {
        Flight flight = flightConverter.convertSaveFlightRequestDtoToFlight(saveFlightRequestDto);
        flight = flightRepo.save(flight);
        return String.valueOf(flight.getId());
    }

    @Override
    public Flight updateFlightTime(UpdateFlightRequestDto updateFlightRequestDto) {
        Flight flight = findFlightById(updateFlightRequestDto.getId());

        LocalDateTime dtoDepartureDate = updateFlightRequestDto.getDepartureDate();
        LocalDateTime dtoArrivalDate = updateFlightRequestDto.getArrivalDate();

        //user may want to change departure date, arrival date or both
        if (dtoDepartureDate != null) {
            flight.setDepartureDate(dtoDepartureDate);
        }

        if (dtoArrivalDate != null) {
            flight.setArrivalDate(dtoArrivalDate);
        }

        //if departure date is after arrival date, throw exception
        if (flight.getDepartureDate().isAfter(flight.getArrivalDate())) {
            throw new BadRequestException("Departure date cannot be after arrival date!");
        }

        //else, save flight with changed dates
        flightRepo.save(flight);

        return flight;
    }

    @Override
    public Flight deleteFlightById(int flightId) {
        //if flight with the id not found, it throws exception
        Flight flight = findFlightById(flightId);

        //else, delete flight
        flightRepo.deleteById(flightId);
        return flight;
    }

    @Override
    public List<PassengerResponseDto> getPassengersByFlightId(int flightId) {
        Flight flight = findFlightById(flightId);
        return passengerConverter.convertPassengerListToPassengerResponseDtoList(flight.getPassengers());
    }


}
