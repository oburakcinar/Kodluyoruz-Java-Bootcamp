package week2.assignment5;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        double ticketPrice = 30.0;
        System.out.println("Please enter your age");
        int age = scanner.nextInt();
        if (age < 18) {
            ticketPrice *= 0.9;
        } else if (age >= 18 && age <= 25) {
            ticketPrice *= 0.95;
        }

        System.out.println("Please enter the number representing the category");
        System.out.println("1) Action");
        System.out.println("2) Comedy");
        System.out.println("3) Drama");
        System.out.println("4) Horror");
        System.out.println("5) Animation");

        int category = scanner.nextInt();
        if (category == 4) {
            ticketPrice *= 0.9;
        }

        System.out.printf("Ticket price: %.2f", ticketPrice);
    }
}
