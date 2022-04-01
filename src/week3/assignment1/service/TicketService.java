package week3.assignment1.service;

import week3.assignment1.flight.Flight;
import week3.assignment1.passenger.Passenger;
import week3.assignment1.passenger.PassengerFlightDetails;
import java.time.LocalDate;


public class TicketService {

    //method can be used for all classes that extend Flight class
    public void takeTicketForPassenger(Flight flight, Passenger passenger, int seatNumber) {
        double ticketPrice = flight.takeTicket(passenger, seatNumber);
        PassengerFlightDetails passengerFlightDetails =
                                           new PassengerFlightDetails(flight, ticketPrice, seatNumber, LocalDate.now());
        passenger.addPurchasedFlight(passengerFlightDetails);
        System.out.printf("Ticket Price: $%.2f\n\n", ticketPrice); //making sure that ticket price has 2 decimal places
    }

    public void returnTicket(Flight flight, Passenger passenger) {
        PassengerFlightDetails flightDetail = passenger.getPassengerFlightDetail(flight);

        //if 3 days passed, return is unavailable
        boolean canReturn = LocalDate.now().isBefore(flightDetail.getPurchaseDate().plusDays(3));
        if (canReturn) {
            flight.returnTicket(passenger, flightDetail.getSeatNumber()); //executing return
            System.out.printf("Success! Your ticket is returned. You will get your $%.2f back in 3 business days.\n\n",
                            flightDetail.getTicketPrice());
            passenger.removeFromPurchasedFlights(flightDetail); //removing this flight from passenger's purchased flights list
        } else {
            System.out.println("I am sorry! Tickets cannot be returned after 3 days passed.\n");
        }
    }

}
