package com.burak.ticketreservation.entity;

public interface IChildDiscount {

    public double applyChildDiscount(Passenger passenger, PassengerFlightDetails passengerFlightDetails, double initialPrice);

}
