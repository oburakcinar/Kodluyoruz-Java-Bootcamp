package com.burak.ticketreservation.service;

import com.burak.ticketreservation.dao.FlightRepository;
import com.burak.ticketreservation.dao.PassengerFlightDetailsRepository;
import com.burak.ticketreservation.dao.PassengerRepository;
import com.burak.ticketreservation.entity.Flight;
import com.burak.ticketreservation.entity.Passenger;
import com.burak.ticketreservation.entity.PassengerFlightDetails;
import com.burak.ticketreservation.entity.THYFlight;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.service.flight.FlightService;
import com.burak.ticketreservation.service.passenger.PassengerService;
import com.burak.ticketreservation.service.passengerFlightDetails.PassengerFlightDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassengerFlightDetailsUnitTest {

    @InjectMocks
    PassengerFlightDetailsServiceImpl passengerFlightDetailsService;

    @Mock
    PassengerFlightDetailsRepository passengerFlightDetailsRepository;

    @Mock
    FlightService flightService;

    @Mock
    PassengerService passengerService;

    @Mock
    FlightRepository flightRepository;

    @Mock
    PassengerRepository passengerRepository;

    @Test
    void testFindById() {
        PassengerFlightDetails mockDetails = mock(PassengerFlightDetails.class);
        when(mockDetails.getId()).thenReturn(1);

        when(passengerFlightDetailsRepository.findById(mockDetails.getId())).thenReturn(Optional.of(mockDetails));

        PassengerFlightDetails details = passengerFlightDetailsService.findById(mockDetails.getId());

        Assertions.assertEquals(details.getId(), mockDetails.getId());
    }

    @Test
    void testFindByFlightIdAndPassengerId() {
        PassengerFlightDetails mockDetails = mock(PassengerFlightDetails.class);
        when(mockDetails.getFlightId()).thenReturn(0);
        when(mockDetails.getPassengerId()).thenReturn(0);

        when(passengerFlightDetailsRepository.findByFlightIdAndPassengerId(mockDetails.getFlightId(), mockDetails.getPassengerId())).thenReturn(mockDetails);

        PassengerFlightDetails details = passengerFlightDetailsService.findByFlightIdAndPassengerId(mockDetails.getFlightId(), mockDetails.getPassengerId());

        Assertions.assertEquals(details.getId(), mockDetails.getId());
    }

    @Test
    void testCheckSeatIsOccupied() {
        Flight mockFlight = mock(Flight.class);
        when(mockFlight.getSeats()).thenReturn(List.of(true,true,true));

        Assertions.assertThrows(BadRequestException.class, () ->
                passengerFlightDetailsService.checkSeatIsOccupied(mockFlight, 0));
    }

    @Test
    void testDeletePassengerFlightDetail() {
        PassengerFlightDetails mockDetails = mock(PassengerFlightDetails.class);
        when(mockDetails.getId()).thenReturn(1);
        when(mockDetails.getPurchaseDate()).thenReturn(LocalDate.now().minusDays(10));

        when(passengerFlightDetailsRepository.findById(mockDetails.getId())).thenReturn(Optional.of(mockDetails));

        Assertions.assertThrows(BadRequestException.class, () ->
                passengerFlightDetailsService.deletePassengerFlightDetail(any()));
    }
}
