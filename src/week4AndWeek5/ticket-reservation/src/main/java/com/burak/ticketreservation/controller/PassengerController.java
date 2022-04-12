package com.burak.ticketreservation.controller;

import com.burak.ticketreservation.entity.Passenger;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.requestDto.passengerRequestDto.SavePassengerRequestDto;
import com.burak.ticketreservation.requestDto.passengerRequestDto.UpdatePassengerRequestDto;
import com.burak.ticketreservation.responseDto.PassengerResponseDto;
import com.burak.ticketreservation.service.flight.FlightService;
import com.burak.ticketreservation.service.passenger.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    private final FlightService flightService;

    //getting all passengers
    @GetMapping("/passengers")
    public ResponseEntity<List<Passenger>> getPassengers() {
        List<Passenger> passengers = passengerService.getPassengers();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    //getting a passenger by passenger id
    @GetMapping("/passengers/{passengerId}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable("passengerId") int passengerId) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    //get a passenger by flight id
    @GetMapping("/passengers/flight/{flightId}")
    public ResponseEntity<List<PassengerResponseDto>> getPassengersByFlightId(@PathVariable int flightId) {
        List<PassengerResponseDto> passengersInFlight = flightService.getPassengersByFlightId(flightId);
        return new ResponseEntity<>(passengersInFlight, HttpStatus.OK);
    }

    @PostMapping("/passengers")
    public ResponseEntity<String> savePassenger(@Valid @RequestBody SavePassengerRequestDto savePassengerRequestDto,
                                                BindingResult theBindingResult) {
        //SavePassengerRequestDto class has data validation rules.
        //If validation rules are violated, it throws an exception and message defined in the class is shown
        if (theBindingResult.hasErrors()) {
            throw new BadRequestException(theBindingResult.getFieldError().getDefaultMessage());
        }

        //saving the passenger if all fields abide validation rules
        String passengerId = passengerService.savePassenger(savePassengerRequestDto);
        return new ResponseEntity<>(passengerId, HttpStatus.OK);
    }

    //updating passenger's age
    @PutMapping("/passengers")
    public ResponseEntity<Passenger> updatePassengerAge(@Valid @RequestBody UpdatePassengerRequestDto updatePassengerRequestDto
                                            ,BindingResult theBindingResult) {
        //if passengers age is negative, it throws exception
        if (theBindingResult.hasErrors()) {
            throw new BadRequestException(theBindingResult.getFieldError().getDefaultMessage());
        }

        Passenger passenger = passengerService.updatePassenger(updatePassengerRequestDto);
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    //deleting passenger by passenger id
    @DeleteMapping("/passengers/{passengerId}")
    public ResponseEntity<PassengerResponseDto> deletePassengerById(@PathVariable int passengerId) {
        PassengerResponseDto passengerResponseDto = passengerService.deletePassengerById(passengerId);
        return new ResponseEntity<>(passengerResponseDto, HttpStatus.OK);
    }
}
