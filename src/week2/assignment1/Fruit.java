package week2.assignment1;

public abstract class Fruit {
    protected String name;
    private double quantity;
    private double unitPrice;

    public Fruit() {

    }

    //name field will be initialized in subclasses according to their class name
    public Fruit(double quantity, double unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
