package week2.assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grocery {
    private List<Fruit> fruitList;

    public Grocery() {
        fruitList = new ArrayList<>();
    }

    public void addFruit(Fruit fruit) {
        fruitList.add(fruit);
    }

    //this method is used for adding n number of fruits at once
    public void addMultipleFruits(Fruit... fruit) {
        fruitList.addAll(Arrays.asList(fruit)); //since addAll methods needs Collection as a parameter, I converted the array to collection
    }

    //getfruit is used for calling the appropriate fruit which will be needed for sell method below
    public Fruit getFruit(String fruitName) {
        for (Fruit fruit : fruitList) {
            if (fruit.getName().equals(fruitName)) {
                return fruit;
            }
        }
        return null;
    }

    public void printFruitList() {
        System.out.println("Fruit List");
        for (Fruit fruit : fruitList) {
            System.out.printf("fruit: %s, stock: %.2f, price(kg): $%.2f\n", fruit.getName(), fruit.getQuantity(), fruit.getUnitPrice());
        }
        System.out.println();
    }

    //it validates the double sellQuantity at first, then uses it for executing the operation
    //since the method makes use of polymorphism, just one sell method is enough for all fruit types(apple,pear,cherry etc.)
    public void sell(Fruit fruit, double sellQuantity) {
        if(sellQuantity <= 0) {
            System.out.println("quantity must be positive number!\n");
            return;
        }

        if (fruit.getQuantity() >= sellQuantity) {
            fruit.setQuantity(fruit.getQuantity() - sellQuantity);
            System.out.printf("You have successfully ordered %.2fkg of %s!\n", sellQuantity, fruit.getName());
            System.out.printf("Total price: $%.2f\n\n", sellQuantity * fruit.getUnitPrice());
        } else {
            System.out.printf("Out of stock! You cannot buy more than %.2fkg of %s\n\n", fruit.getQuantity(), fruit.getName());
        }
    }
}
