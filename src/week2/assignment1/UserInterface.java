package week2.assignment1;

import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private Scanner scan;
    private Grocery grocery;

    public UserInterface(Scanner scan, Grocery grocery) {
        this.scan = scan;
        this.grocery = grocery;
    }

    public void start() {
        System.out.println("Welcome to the grocery!\n");
        
        while (true) {
            grocery.printFruitList();
            System.out.println("Enter the name of the fruit or press e to exit");
            String command = scan.next();

            if (command.equalsIgnoreCase("e")) { //exit case
                System.out.println("Bye!");
                System.exit(0); //this will terminate the app
            }

            Fruit fruit = grocery.getFruit(command.toLowerCase());

            if (fruit == null) { //in case of user does not follow the guide and enters inappropriate value
                System.out.println("Unknown command! Please enter a valid fruit name.\n");
                continue;
            } else {
                System.out.println("Enter the amount");
                double sellAmount = scan.nextDouble();
                grocery.sell(fruit, sellAmount);
            }
        }
    }

}
