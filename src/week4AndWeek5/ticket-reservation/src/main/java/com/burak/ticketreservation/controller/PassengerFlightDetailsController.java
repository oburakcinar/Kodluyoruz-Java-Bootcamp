package com.burak.ticketreservation.controller;

import com.burak.ticketreservation.entity.PassengerFlightDetails;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.requestDto.passengerFlightDetailsRequestDto.SavePassengerFlightDetailsRequestDto;
import com.burak.ticketreservation.service.passengerFlightDetails.PassengerFlightDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PassengerFlightDetailsController {

    private final PassengerFlightDetailsService passengerFlightDetailsService;

    //getting passenger's flight details by passengerflightdetails id
    @GetMapping("/passengerFlightDetails/{passengerFlightDetailsId}")
    public ResponseEntity<PassengerFlightDetails> getPassengerFlightDetails(@PathVariable int passengerFlightDetailsId) {
        PassengerFlightDetails passengerFlightDetails = passengerFlightDetailsService.findById(passengerFlightDetailsId);
        return new ResponseEntity<>(passengerFlightDetails, HttpStatus.OK);
    }

    /////////////////////////bozuk
    //getting passenger's flight details by passenger and flight id
    @GetMapping("/passengerFlightDetails")
    public ResponseEntity<PassengerFlightDetails> getPassengerFlightDetailsByFlightAndPassengerId(
            @RequestParam("flightId") int flightId, @RequestParam("passengerId") int passengerId) {
        PassengerFlightDetails passengerFlightDetails = passengerFlightDetailsService
                            .findByFlightIdAndPassengerId(flightId, passengerId);
        return new ResponseEntity<>(passengerFlightDetails, HttpStatus.OK);
    }

    //buying a ticket for a passenger
    @PostMapping("/passengerFlightDetails")
    public ResponseEntity<String> savePassengerFlightDetails(@Valid @RequestBody SavePassengerFlightDetailsRequestDto savePassengerFlightDetailsRequestDto,
                                                             BindingResult theBindingResult) {
        if(theBindingResult.hasErrors()) {
            throw new BadRequestException(theBindingResult.getFieldError().getDefaultMessage());
        }
        String id = passengerFlightDetailsService.savePassengerFlightDetail(savePassengerFlightDetailsRequestDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //returning a passenger's ticket
    @DeleteMapping("/passengerFlightDetails/{passengerFlightDetailsId}")
    public ResponseEntity<String> returnTicket(@PathVariable int passengerFlightDetailsId) {
        String successMessage = passengerFlightDetailsService.deletePassengerFlightDetail(passengerFlightDetailsId);
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
