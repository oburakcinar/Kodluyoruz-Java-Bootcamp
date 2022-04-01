package week3.assignment1.passenger;

import week3.assignment1.flight.Flight;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private long id;
    private String name;
    private int age;
    private List<PassengerFlightDetails> purchasedFlights;
    private boolean currentFlightBusinessClass;

    public Passenger(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        purchasedFlights = new ArrayList<>();
    }


    public void addPurchasedFlight(PassengerFlightDetails passengerFlightDetails) {
        purchasedFlights.add(passengerFlightDetails);
    }

    public void removeFromPurchasedFlights(PassengerFlightDetails passengerFlightDetails) {
        purchasedFlights.remove(passengerFlightDetails);
    }

    public PassengerFlightDetails getPassengerFlightDetail(Flight flight) {
        for (PassengerFlightDetails pfd : purchasedFlights) {
            if (pfd.getFlight() == flight) {
                return pfd;
            }
        }
        return null;
    }

    public List<Flight> getAllPurchasedFlights() {
        List<Flight> flights = new ArrayList<>();
        for (PassengerFlightDetails passengerFlightDetails : purchasedFlights) {
            flights.add(passengerFlightDetails.getFlight());
        }
        return flights;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PassengerFlightDetails> getPurchasedFlights() {
        return purchasedFlights;
    }

    public int getAge() {
        return age;
    }

    public boolean isCurrentFlightBusinessClass() {
        return currentFlightBusinessClass;
    }

    public void setCurrentFlightBusinessClass(boolean currentFlightBusinessClass) {
        this.currentFlightBusinessClass = currentFlightBusinessClass;
    }
}
