package com.burak.ticketreservation.service;

import com.burak.ticketreservation.converter.FlightConverter;
import com.burak.ticketreservation.dao.FlightRepository;
import com.burak.ticketreservation.entity.Flight;
import com.burak.ticketreservation.entity.THYFlight;
import com.burak.ticketreservation.requestDto.flightRequestDto.SaveFlightRequestDto;
import com.burak.ticketreservation.requestDto.flightRequestDto.UpdateFlightRequestDto;
import com.burak.ticketreservation.service.flight.FlightServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FlightServiceUnitTest {

    @InjectMocks
    FlightServiceImpl flightService;


    @Mock
    FlightRepository<Flight> flightRepo;


    @Mock
    FlightConverter flightConverter;


    @Test
    void testSaveFlight() {
        //create mock save flight request dto
        SaveFlightRequestDto saveFlightRequestDto = mock(SaveFlightRequestDto.class);

        //create mock flight
        Flight flight = mock(Flight.class);
        when(flight.getId()).thenReturn(1);


        when(flightConverter.convertSaveFlightRequestDtoToFlight(any())).thenReturn(flight);
        when(flightRepo.save(any())).thenReturn(flight);
        String id = flightService.saveFlight(saveFlightRequestDto);
        Assertions.assertEquals(String.valueOf(flight.getId()), id);

    }

    @Test
    void testFindFlightById() {
        Flight mockFlight = mock(Flight.class);
        when(mockFlight.getId()).thenReturn(1);

        when(flightRepo.findById(mockFlight.getId())).thenReturn(Optional.of(mockFlight));

        Flight flight = flightService.findFlightById(mockFlight.getId());

        Assertions.assertEquals(flight.getId(), mockFlight.getId());
    }

    @Test
    void testUpdateFlightTime() {

        LocalDateTime initialDepartureDate = LocalDateTime.now().plusHours(1);
        LocalDateTime initialArrivalDate = LocalDateTime.now().plusHours(3);

        Flight f = new THYFlight();
        f.setId(0);
        f.setDepartureDate(initialDepartureDate);
        f.setArrivalDate(initialArrivalDate);

        UpdateFlightRequestDto updateFlightRequestDto = mock(UpdateFlightRequestDto.class);
        when(updateFlightRequestDto.getDepartureDate()).thenReturn(LocalDateTime.now().plusHours(3));
        when(updateFlightRequestDto.getArrivalDate()).thenReturn(LocalDateTime.now().plusHours(5));

        when(flightRepo.findById(any())).thenReturn(Optional.of(f));

        Flight flight = flightService.updateFlightTime(updateFlightRequestDto);

        Assertions.assertNotEquals(initialDepartureDate, flight.getDepartureDate());

    }

    @Test
    void testDeleteFlight() {
        Flight mockFlight = mock(Flight.class);
        when(mockFlight.getId()).thenReturn(1);

        when(flightRepo.findById(mockFlight.getId())).thenReturn(Optional.of(mockFlight));
        Flight deletedFlight = flightService.deleteFlightById(mockFlight.getId());

        Assertions.assertEquals(mockFlight, deletedFlight);
    }

}


