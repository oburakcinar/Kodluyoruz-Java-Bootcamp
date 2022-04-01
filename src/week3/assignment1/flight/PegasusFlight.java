package week3.assignment1.flight;

import week3.assignment1.passenger.Passenger;

import java.time.LocalDateTime;

public class PegasusFlight extends Flight implements IAbroadFoodChoice, IChildDiscount {

    public PegasusFlight(String fromCity, String toCity, LocalDateTime departureDate, LocalDateTime arrivalDate, int seatCapacity, double price) {
        super(fromCity, toCity, departureDate, arrivalDate, seatCapacity, price);
    }

    @Override
    public void foodChoice() {
        System.out.println("Pegasus serves meat meals.");
    }

    @Override
    public double calculateTicketPrice(Passenger passenger) {
        double initialTicketPrice = super.calculateTicketPrice(passenger);
        return applyChildDiscount(passenger, initialTicketPrice);
    }

    @Override
    public double applyChildDiscount(Passenger passenger, double initialPrice) {
        if (passenger.getAge() <= 12) {
            System.out.println("Applying 20% discount for children between 0-12 years old...");
            return initialPrice * 0.8; //20% off
        } else if (passenger.getAge() <= 18){
            System.out.println("Applying 10% discount for children between 13-18 years old...");
            return initialPrice * 0.9; //10% off
        } else {
            return initialPrice; //no discount above age 18
        }
    }
}
