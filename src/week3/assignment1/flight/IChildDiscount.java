package week3.assignment1.flight;

import week3.assignment1.passenger.Passenger;

public interface IChildDiscount {

    public double applyChildDiscount(Passenger passenger, double initialPrice);

}
