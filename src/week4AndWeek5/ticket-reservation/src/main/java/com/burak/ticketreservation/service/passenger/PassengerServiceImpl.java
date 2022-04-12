package com.burak.ticketreservation.service.passenger;

import com.burak.ticketreservation.converter.PassengerConverter;
import com.burak.ticketreservation.dao.PassengerRepository;
import com.burak.ticketreservation.entity.Passenger;
import com.burak.ticketreservation.exception.EntityNotFoundException;
import com.burak.ticketreservation.requestDto.passengerRequestDto.SavePassengerRequestDto;
import com.burak.ticketreservation.requestDto.passengerRequestDto.UpdatePassengerRequestDto;
import com.burak.ticketreservation.responseDto.PassengerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    private final PassengerConverter passengerConverter;

    @Override
    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassengerById(int id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);

        if (passenger.isPresent()) {
            return passenger.get();
        } else {
            throw new EntityNotFoundException("Passenger id not found - " + id);
        }
    }

    @Override
    public String savePassenger(SavePassengerRequestDto savePassengerRequestDto) {
        Passenger passenger = passengerConverter.convertPassengerDtoToPassenger(savePassengerRequestDto);
        passengerRepository.save(passenger);
        return String.valueOf(passenger.getId());
    }

    @Override
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(UpdatePassengerRequestDto updatePassengerRequestDto) {
        Passenger passenger = getPassengerById(updatePassengerRequestDto.getId());
        passenger.setAge(updatePassengerRequestDto.getAge());
        passengerRepository.save(passenger);
        return passenger;
    }

    @Override
    public PassengerResponseDto deletePassengerById(int passengerId) {
        Passenger passenger = getPassengerById(passengerId);
        passengerRepository.delete(passenger);
        return passengerConverter.convertPassengerToPassengerResponseDto(passenger);
    }
}
