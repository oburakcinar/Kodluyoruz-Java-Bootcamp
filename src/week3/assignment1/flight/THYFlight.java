package week3.assignment1.flight;

import week3.assignment1.passenger.Passenger;

import java.time.LocalDateTime;

public class THYFlight extends Flight implements IBusinessClass {

    public THYFlight(String fromCity, String toCity, LocalDateTime departureDate, LocalDateTime arrivalDate, int seatCapacity, double price) {
        super(fromCity, toCity, departureDate, arrivalDate, seatCapacity, price);
    }

    @Override
    public double calculateTicketPrice(Passenger passenger) {
        double ticketPrice = super.calculateTicketPrice(passenger);
        if (passenger.isCurrentFlightBusinessClass()) {
            return calculateBusinessClassPrice(ticketPrice);
        }
        return ticketPrice;
    }

    @Override
    public double calculateBusinessClassPrice(double initialPrice) {
        return initialPrice + businessFee;
    }
}
