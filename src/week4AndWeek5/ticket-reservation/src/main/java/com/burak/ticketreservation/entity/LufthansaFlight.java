package com.burak.ticketreservation.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity(name = "lufthansa_flight")
@DiscriminatorValue("Lufthansa")
public class LufthansaFlight extends Flight implements IChildDiscount {


    @Override
    public double calculateTicketPrice(Passenger passenger, PassengerFlightDetails passengerFlightDetails) {
        double initialTicketPrice = super.calculateTicketPrice(passenger, passengerFlightDetails);
        return applyChildDiscount(passenger, passengerFlightDetails, initialTicketPrice);
    }

    @Override
    public double applyChildDiscount(Passenger passenger, PassengerFlightDetails passengerFlightDetails, double initialPrice) {
        if (passenger.getAge() <= 4) {
            return initialPrice * 0.2; //Applying 80% discount for children between 0-4 years old...
        } else if (passenger.getAge() <= 12){
            return initialPrice * 0.6; //Applying 40% discount for children between 5-12 years old...
        } else if (passenger.getAge() <= 18){
            return initialPrice * 0.75; //Applying 25% discount for children between 13-18 years old...
        } else {
            return initialPrice; //no discount for age above 18
        }
    }

}
