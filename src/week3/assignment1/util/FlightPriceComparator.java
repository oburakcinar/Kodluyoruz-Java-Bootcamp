package week3.assignment1.util;

import week3.assignment1.flight.Flight;

import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight> {
    @Override
    public int compare(Flight flight1, Flight flight2) { //sorting flights by ascending ticket price
        return (int) (flight1.getBaseTicketPrice() - flight2.getBaseTicketPrice());
    }
}
