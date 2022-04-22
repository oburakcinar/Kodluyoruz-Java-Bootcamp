package com.burak.ticketreservation.service;

import com.burak.ticketreservation.converter.PassengerConverter;
import com.burak.ticketreservation.dao.PassengerRepository;
import com.burak.ticketreservation.entity.Passenger;
import com.burak.ticketreservation.requestDto.passengerRequestDto.SavePassengerRequestDto;
import com.burak.ticketreservation.service.passenger.PassengerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassengerServiceUnitTest {

    @InjectMocks
    PassengerServiceImpl passengerService;

    @Mock
    PassengerRepository passengerRepository;

    @Mock
    PassengerConverter passengerConverter;

    @Test
    void testGetPassengerById() {
        Passenger mockPassenger = mock(Passenger.class);
        when(mockPassenger.getId()).thenReturn(1);

        when(passengerRepository.findById(mockPassenger.getId())).thenReturn(Optional.of(mockPassenger));

        Passenger passenger = passengerService.getPassengerById(mockPassenger.getId());
        Assertions.assertEquals(passenger.getId(), mockPassenger.getId());
    }

    @Test
    void testSavePassenger() {
        Passenger mockPassenger = mock(Passenger.class);
        when(mockPassenger.getId()).thenReturn(1);

        SavePassengerRequestDto mockDto = mock(SavePassengerRequestDto.class);

        when(passengerConverter.convertPassengerDtoToPassenger(any())).thenReturn(mockPassenger);

        int savedPassengerId = Integer.parseInt(passengerService.savePassenger(mockDto));
        Assertions.assertEquals(savedPassengerId, mockPassenger.getId());
    }

}
