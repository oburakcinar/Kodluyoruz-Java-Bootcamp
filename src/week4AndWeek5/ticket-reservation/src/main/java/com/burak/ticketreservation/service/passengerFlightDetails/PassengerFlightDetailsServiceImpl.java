package com.burak.ticketreservation.service.passengerFlightDetails;

import com.burak.ticketreservation.converter.FlightConverter;
import com.burak.ticketreservation.converter.PassengerFlightDetailsConverter;
import com.burak.ticketreservation.dao.PassengerFlightDetailsRepository;
import com.burak.ticketreservation.entity.Flight;
import com.burak.ticketreservation.entity.Passenger;
import com.burak.ticketreservation.entity.PassengerFlightDetails;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.exception.EntityAlreadyExistsException;
import com.burak.ticketreservation.exception.EntityNotFoundException;
import com.burak.ticketreservation.requestDto.passengerFlightDetailsRequestDto.SavePassengerFlightDetailsRequestDto;
import com.burak.ticketreservation.responseDto.FlightResponseDto;
import com.burak.ticketreservation.service.flight.FlightService;
import com.burak.ticketreservation.service.passenger.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PassengerFlightDetailsServiceImpl implements PassengerFlightDetailsService{


    private final PassengerFlightDetailsRepository passengerFlightDetailsRepository;


    private final PassengerFlightDetailsConverter passengerFlightDetailsConverter;


    private final FlightService flightService;


    private final PassengerService passengerService;


    private final FlightConverter flightConverter;


    @Override
    public PassengerFlightDetails findById(int passengerFlightDetailsId) {
        Optional<PassengerFlightDetails> passengerFlightDetails = passengerFlightDetailsRepository.findById(passengerFlightDetailsId);
        if (passengerFlightDetails.isPresent()) {
            return passengerFlightDetails.get();
        } else {
            throw new EntityNotFoundException("PassengerFlightDetails id not found - " + passengerFlightDetailsId);
        }
    }

    @Override
    public PassengerFlightDetails findByFlightIdAndPassengerId(int flightId, int passengerId) {

        flightService.findFlightById(flightId); //calling this method to validate flight with the flightId exists
        passengerService.getPassengerById(passengerId); //calling this method to validate passenger with the passengerId exists
        //If either flight or passenger does not exist, it throws entitynotfoundexception

        //If both of them exist, get passengerflightdetails object
        PassengerFlightDetails passengerFlightDetails = passengerFlightDetailsRepository.findByFlightIdAndPassengerId(flightId, passengerId);

        //if passengerflightdetails object is null, throw exception
        if(passengerFlightDetails == null) {
            throw new BadRequestException("There is no ticket with entered flight and passenger id");
        }

        //else return
        return passengerFlightDetails;
    }

    @Override
    public List<FlightResponseDto> findFlightsByPassengerId(int passengerId) {
        //calling getPassengerById method to check if passenger with passenger id exists. if not, this method throws entity not found exception
        passengerService.getPassengerById(passengerId);

        List<PassengerFlightDetails> passengerFlightDetailsList = passengerFlightDetailsRepository.findAllByPassengerId(passengerId);

        List<FlightResponseDto> flightResponseDtoList = new ArrayList<>();
        for (PassengerFlightDetails pfd : passengerFlightDetailsList) {
            Flight flight = flightService.findFlightById(pfd.getFlightId());
            flightResponseDtoList.add(flightConverter.convertFlightToFlightResponseDto(flight, pfd));
        }
        return flightResponseDtoList;
    }

    //seat validation
    @Override
    public String savePassengerFlightDetail(SavePassengerFlightDetailsRequestDto savePassengerFlightDetailsRequestDto) {
        //
        int flightId = savePassengerFlightDetailsRequestDto.getFlightId();
        int passengerId = savePassengerFlightDetailsRequestDto.getPassengerId();
        Flight flight = flightService.findFlightById(flightId);
        int seatNumber = savePassengerFlightDetailsRequestDto.getSeatNumber();

        //passenger cannot take more than 1 ticket with the same id and name
        checkPassengerFlightAlreadyExists(flightId, passengerId);

        //checking if the seat is occupied
        checkSeatIsOccupied(flight, seatNumber);

        PassengerFlightDetails passengerFlightDetails = passengerFlightDetailsConverter
                .convertPassengerFlightDetailsDtoToPassengerFlightDetails(savePassengerFlightDetailsRequestDto);

        takeTicket(passengerFlightDetails);

        return String.valueOf(passengerFlightDetails.getId());
    }

    //returning passenger's ticket
    @Override
    public String deletePassengerFlightDetail(int passengerFlightDetailId) {
        PassengerFlightDetails passengerFlightDetails = passengerFlightDetailsRepository.findById(passengerFlightDetailId).get();

        //Checking when the ticket is sold
        //if 3 days passed after the ticket is sold, ticket cannot be returned
        boolean canReturn = LocalDate.now().isBefore(passengerFlightDetails.getPurchaseDate().plusDays(3));
        if (canReturn) {
            returnTicket(passengerFlightDetails);
            return String.format("Success! The ticket (id = %s) is returned. You will get your $%.2f back in 3 business days.",
                    passengerFlightDetails.getId(), passengerFlightDetails.getTicketPrice());
        } else {
            throw new BadRequestException("Ticket cannot be returned after 3 days passed!");
        }
    }


    private void returnTicket(PassengerFlightDetails passengerFlightDetails) {
        Flight flight = flightService.findFlightById(passengerFlightDetails.getFlightId());
        int seatNumber = passengerFlightDetails.getSeatNumber();
        flight.getSeats().set(seatNumber, false); //changing the seat's status to empty
        passengerFlightDetailsRepository.delete(passengerFlightDetails);
    }


    private double calculateTicketPrice(Flight flight, Passenger passenger, PassengerFlightDetails passengerFlightDetails) {
        return flight.calculateTicketPrice(passenger, passengerFlightDetails);
    }

    private void takeTicket(PassengerFlightDetails passengerFlightDetails) {
        Flight flight = flightService.findFlightById(passengerFlightDetails.getFlightId());
        Passenger passenger = passengerService.getPassengerById(passengerFlightDetails.getPassengerId());

        int seatNumber = passengerFlightDetails.getSeatNumber();
        flight.getSeats().set(seatNumber, true); //making the seat's status occupied

        double ticketPrice = calculateTicketPrice(flight, passenger, passengerFlightDetails);
        passengerFlightDetails.setTicketPrice(ticketPrice);
        passengerFlightDetailsRepository.save(passengerFlightDetails);
    }

    private void checkPassengerFlightAlreadyExists(int flightId, int passengerId) {
        PassengerFlightDetails passengerFlightDetails = findByFlightIdAndPassengerId(flightId, passengerId);
        if (passengerFlightDetails != null) {
            throw new EntityAlreadyExistsException("Passenger is already registered to the flight");
        }
    }

    private void checkSeatIsOccupied(Flight flight, int seatNumber) {
        if (flight.getSeats().get(seatNumber) == true) {
            throw new BadRequestException("Seat is occupied!");
        }
    }
}
