package com.burak.ticketreservation.converter;

import com.burak.ticketreservation.entity.*;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.requestDto.passengerFlightDetailsRequestDto.SavePassengerFlightDetailsRequestDto;
import com.burak.ticketreservation.service.flight.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PassengerFlightDetailsConverter {

    private final FlightService flightService;


    public PassengerFlightDetails convertPassengerFlightDetailsDtoToPassengerFlightDetails(
            SavePassengerFlightDetailsRequestDto savePassengerFlightDetailsRequestDto) {
        //flight is needed for checking whether business class option is valid or not
        Flight flight = flightService.findFlightById(savePassengerFlightDetailsRequestDto.getFlightId());

        PassengerFlightDetails passengerFlightDetails = new PassengerFlightDetails();
        passengerFlightDetails.setFlightId(savePassengerFlightDetailsRequestDto.getFlightId());
        passengerFlightDetails.setPassengerId(savePassengerFlightDetailsRequestDto.getPassengerId());
        passengerFlightDetails.setSeatNumber(savePassengerFlightDetailsRequestDto.getSeatNumber());

        //purchase date is needed when passenger wants to return his/her flight ticket
        //If 3 days passed after buying the ticket, passenger cannot return the ticket
        passengerFlightDetails.setPurchaseDate(LocalDate.now()); //purchase date is now

        if (savePassengerFlightDetailsRequestDto.isBusiness()) {
            if (flight instanceof IBusinessClass) {
                passengerFlightDetails.setBusiness(true);
            } else {
                //if flight does not implement IBusinessClass, selecting business class is not allowed
                throw new BadRequestException("This flight does not have business class option");
            }
        }
        // if savePassengerFlightDetailsRequestDto.isBusiness() == false, no operation is needed since it is default value for boolean type

        return passengerFlightDetails;
    }

}
