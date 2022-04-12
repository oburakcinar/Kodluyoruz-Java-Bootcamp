package com.burak.ticketreservation.controller;

import com.burak.ticketreservation.entity.Flight;
import com.burak.ticketreservation.entity.LufthansaFlight;
import com.burak.ticketreservation.entity.PegasusFlight;
import com.burak.ticketreservation.entity.THYFlight;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.requestDto.flightRequestDto.SaveFlightRequestDto;
import com.burak.ticketreservation.requestDto.flightRequestDto.UpdateFlightRequestDto;
import com.burak.ticketreservation.responseDto.FlightResponseDto;
import com.burak.ticketreservation.service.flight.FlightService;
import com.burak.ticketreservation.service.passengerFlightDetails.PassengerFlightDetailsService;
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
public class FlightController {

    private final FlightService flightService;

    private final PassengerFlightDetailsService passengerFlightDetailsService;

    //getting all flights
    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = flightService.findAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    //getting all thy flights
    @GetMapping("/thyflights")
    public ResponseEntity<List<THYFlight>> getTHYFlights() {
        List<THYFlight> flights = flightService.findAllTHYFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    //getting all pegasus flights
    @GetMapping("/pegasusflights")
    public ResponseEntity<List<PegasusFlight>> getPegasusFlights() {
        List<PegasusFlight> flights = flightService.findAllPegasusFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    //getting all lufthansa flights
    @GetMapping("/lufthansaflights")
    public ResponseEntity<List<LufthansaFlight>> getLufthansaFlights() {
        List<LufthansaFlight> flights = flightService.findAllLufthansaFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    //getting all flights by passenger id
    @GetMapping("/passengerflights")
    public ResponseEntity<List<FlightResponseDto>> getFlightsByPassengerId(@RequestParam("passengerId") int passengerId) {
        List<FlightResponseDto> flightResponseDtoList = passengerFlightDetailsService.findFlightsByPassengerId(passengerId);
        return new ResponseEntity<>(flightResponseDtoList, HttpStatus.OK);
    }

    //getting flight by flight id
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("flightId") int flightId) {
        Flight flight = flightService.findFlightById(flightId);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    //creating a new flight
    @PostMapping("/flights")
    public ResponseEntity<String> saveFlight(@Valid @RequestBody SaveFlightRequestDto saveFlightRequestDto,
                                             BindingResult theBindingResult) {
        //SaveFlightRequestDto class has data validation rules.
        //If validation rules are violated, it throws an exception and message defined in the class is shown
        if(theBindingResult.hasErrors()) {
            throw new BadRequestException(theBindingResult.getFieldError().getDefaultMessage());
        }

        //saving the flight if all fields abide validation rules
        String flightId = flightService.saveFlight(saveFlightRequestDto);
        return new ResponseEntity<>(flightId, HttpStatus.OK);
    }

    //updating flight's departure and/or arrival time
    @PutMapping("/flights")
    public ResponseEntity<Flight> updateFlightTime(@Valid @RequestBody UpdateFlightRequestDto updateFlightRequestDto,
                                                   BindingResult theBindingResult) {

        //UpdateFlightRequestDto class has data validation rules.
        //Arrival time must be after departure time and both times must be future time
        //If validation rules are violated, it throws an exception and message defined in the class is shown
        if(theBindingResult.hasErrors()) {
            throw new BadRequestException(theBindingResult.getFieldError().getDefaultMessage());
        }

        //updating the flight if all fields abide validation rules
        Flight flight = flightService.updateFlightTime(updateFlightRequestDto);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    //deleting a flight
    @DeleteMapping("/flights/{flightId}")
    public ResponseEntity<Flight> deleteFlightById(@PathVariable int flightId) {
        Flight flight = flightService.deleteFlightById(flightId);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
}
