package week3.assignment1.flight;

public interface IBusinessClass {
    static final int businessFee = 500; //constant fee to upgrade the ticket to business class

    public double calculateBusinessClassPrice(double initialPrice);
}
