package com.burak.ticketreservation.converter;

import com.burak.ticketreservation.entity.*;
import com.burak.ticketreservation.exception.BadRequestException;
import com.burak.ticketreservation.requestDto.flightRequestDto.SaveFlightRequestDto;
import com.burak.ticketreservation.responseDto.FlightResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightConverter {


    public Flight convertSaveFlightRequestDtoToFlight(SaveFlightRequestDto saveFlightRequestDto) {
        //checking that arrival date must be after departure date
        //If not, no need to continue process. An exception will be thrown
        LocalDateTime departureDate = saveFlightRequestDto.getDepartureDate();
        LocalDateTime arrivalDate = saveFlightRequestDto.getArrivalDate();
        validateDepartureAndArrivalDate(departureDate, arrivalDate);

        //If dates are validated, start converting

        //creating a flight object depending on string airline
        String airline = saveFlightRequestDto.getAirline().toLowerCase();
        Flight flight = createFlightByAirline(airline);

        //transfering dto data to newly created flight object
        flight.setFromCity(saveFlightRequestDto.getFromCity());
        flight.setToCity(saveFlightRequestDto.getToCity());
        flight.setPassengers(new ArrayList<>());
        flight.setDepartureDate(departureDate);
        flight.setArrivalDate(arrivalDate);
        flight.setBaseTicketPrice(saveFlightRequestDto.getBaseTicketPrice());
        int seatCapacity = saveFlightRequestDto.getSeatCapacity();
        flight.setSeats(createSeatsForFlight(seatCapacity));

        return flight;
    }

    public FlightResponseDto convertFlightToFlightResponseDto(Flight flight, PassengerFlightDetails passengerFlightDetails) {
        //transferring flight data to newly created FlightResponseDto object
        FlightResponseDto flightResponseDto = new FlightResponseDto();
        flightResponseDto.setFlightId(flight.getId());
        flightResponseDto.setFromCity(flight.getFromCity());
        flightResponseDto.setToCity(flight.getToCity());
        flightResponseDto.setDepartureDate(flight.getDepartureDate());
        flightResponseDto.setArrivalDate(flight.getArrivalDate());
        flightResponseDto.setTicketPrice(passengerFlightDetails.getTicketPrice());
        flightResponseDto.setBusiness(passengerFlightDetails.isBusiness());
        return flightResponseDto;
    }


    private Flight createFlightByAirline(String airline) {
        //String airline must be thy, pegasus, or lufthansa since special @Airline annotation does not accept any other value
        //@Airline is used in SaveFlightRequestDto. When creating new flight, if any value other than thy, pegasus, or
        //lufthansa is entered, it throws bad request exception.
        switch (airline) {
            case "thy":
                return new THYFlight();
            case "pegasus":
                return new PegasusFlight();
            case "lufthansa":
                return new LufthansaFlight();
        }
        return null;
    }

    private void validateDepartureAndArrivalDate(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        //arrival date must be after departure date
        if (departureDate.isAfter(arrivalDate)) {
            throw new BadRequestException("Departure date cannot be after arrivalDate!");
        }
    }

    private List<Boolean> createSeatsForFlight(int seatCapacity) {
        //when seats are initialized, all seats value must be false which means all seats are empty
        List<Boolean> seats = new ArrayList<>();
        for (int i = 0; i < seatCapacity; i++) {
            seats.add(false);
        }
        return seats;
    }
}
