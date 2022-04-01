package week3.assignment1.util;

import week3.assignment1.flight.Flight;

import java.util.Comparator;

public class FlightDestinationComparator implements Comparator<Flight> {

    @Override
    public int compare(Flight flight1, Flight flight2) { //sorting flight list alphabetically
        return flight1.getFromCity().compareTo(flight2.getFromCity());
    }

}
