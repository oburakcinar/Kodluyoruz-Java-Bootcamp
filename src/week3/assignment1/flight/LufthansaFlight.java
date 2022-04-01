package week3.assignment1.flight;

import week3.assignment1.passenger.Passenger;

import java.time.LocalDateTime;

public class LufthansaFlight extends Flight implements IChildDiscount {

    public LufthansaFlight(String fromCity, String toCity, LocalDateTime departureDate, LocalDateTime arrivalDate, int seatCapacity, double price) {
        super(fromCity, toCity, departureDate, arrivalDate, seatCapacity, price);
    }

    @Override
    public double calculateTicketPrice(Passenger passenger) {
        double initialTicketPrice = super.calculateTicketPrice(passenger);
        return applyChildDiscount(passenger, initialTicketPrice);
    }

    @Override
    public double applyChildDiscount(Passenger passenger, double initialPrice) {
        if (passenger.getAge() <= 4) {
            System.out.println("Applying 80% discount for children between 0-4 years old...");
            return initialPrice * 0.2; //80% off
        } else if (passenger.getAge() <= 12){
            System.out.println("Applying 40% discount for children between 5-12 years old...");
            return initialPrice * 0.6; //40% off
        } else if (passenger.getAge() <= 18){
            System.out.println("Applying 25% discount for children between 13-18 years old...");
            return initialPrice * 0.75; //25% off
        } else {
            return initialPrice; //no discount above age 18
        }
    }

}
