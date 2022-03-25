package week2.assignment1;

public class Stock {
    private double appleAmount;
    private double pearAmount;
    private double cherryAmount;

    public Stock(double appleAmount, double pearAmount, double cherryAmount) {
        this.appleAmount = appleAmount;
        this.pearAmount = pearAmount;
        this.cherryAmount = cherryAmount;
    }

    public double getAppleAmount() {
        return appleAmount;
    }

    public double getPearAmount() {
        return pearAmount;
    }

    public double getCherryAmount() {
        return cherryAmount;
    }

    public void sellApple(double sellAmount) {
        if (appleAmount >= sellAmount) {
            appleAmount -= sellAmount;
            System.out.println("Order is successful!");
        } else {
            System.out.println("Out of stock! You cannot buy more than " + appleAmount + "kg of apple");
        }
    }

    public void sellPear(double sellAmount) {
        if (pearAmount >= sellAmount) {
            pearAmount -= sellAmount;
            System.out.println("Order is successful!");
        } else {
            System.out.println("Out of stock! You cannot buy more than " + pearAmount + "kg of pear");
        }
    }

    public void sellCherry(double sellAmount) {
        if (cherryAmount >= sellAmount) {
            cherryAmount -= sellAmount;
            System.out.println("Order is successful!");
        } else {
            System.out.println("Out of stock! You cannot buy more than " + cherryAmount + "kg of cherry");
        }
    }
}
