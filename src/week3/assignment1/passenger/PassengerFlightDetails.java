package week3.assignment1.passenger;

import week3.assignment1.flight.Flight;

import java.time.LocalDate;

//this class stores the flight details (seat number, purchase time etc.) which will be needed when passenger wants to return the ticket

public class PassengerFlightDetails {
    private Flight flight;
    private double ticketPrice;
    private int seatNumber;
    private LocalDate purchaseDate;

    public PassengerFlightDetails(Flight flight, double ticketPrice, int seatNumber, LocalDate purchaseDate) {
        this.flight = flight;
        this.ticketPrice = ticketPrice;
        this.seatNumber = seatNumber;
        this.purchaseDate = purchaseDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
