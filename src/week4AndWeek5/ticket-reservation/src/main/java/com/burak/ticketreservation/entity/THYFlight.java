package com.burak.ticketreservation.entity;


import javax.persistence.*;


@Entity(name = "thy_flight")
@DiscriminatorValue("THY")
public class THYFlight extends Flight implements IBusinessClass {

    @Override
    public double calculateTicketPrice(Passenger passenger, PassengerFlightDetails passengerFlightDetails) {
        double ticketPrice = super.calculateTicketPrice(passenger, passengerFlightDetails);
        if (passengerFlightDetails.isBusiness()) {
            return calculateBusinessClassPrice(ticketPrice);
        }
        return ticketPrice;
    }

    @Override
    public double calculateBusinessClassPrice(double initialPrice) {
        return initialPrice + businessFee;
    }
}
