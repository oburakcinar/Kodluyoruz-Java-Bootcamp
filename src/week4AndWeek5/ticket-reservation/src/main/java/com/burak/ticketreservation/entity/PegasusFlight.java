package com.burak.ticketreservation.entity;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity(name = "pegasus_flight")
@DiscriminatorValue("Pegasus")
public class PegasusFlight extends Flight implements IAbroadFoodChoice, IChildDiscount {


    @Override
    public void foodChoice() {
        System.out.println("Pegasus serves meat meals.");
    }

    @Override
    public double calculateTicketPrice(Passenger passenger, PassengerFlightDetails passengerFlightDetails) {
        double initialTicketPrice = super.calculateTicketPrice(passenger, passengerFlightDetails);
        return applyChildDiscount(passenger, passengerFlightDetails, initialTicketPrice);
    }

    @Override
    public double applyChildDiscount(Passenger passenger, PassengerFlightDetails passengerFlightDetails, double initialPrice) {
        if (passenger.getAge() <= 12) {
            return initialPrice * 0.8; //Applying 20% discount for children between 0-12 years old...
        } else if (passenger.getAge() <= 18){
            return initialPrice * 0.9; //Applying 10% discount for children between 13-18 years old...
        } else {
            return initialPrice; //no discount above age 18
        }
    }
}
