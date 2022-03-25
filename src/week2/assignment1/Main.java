package week2.assignment1;

import java.util.Scanner;

//kullanicidan veri al odevi

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the amounts of apple, pear, and cherry in the stock");
        double appleStock = scan.nextDouble();
        double pearStock = scan.nextDouble();
        double cherryStock = scan.nextDouble();

        Stock stock = new Stock(appleStock, pearStock, cherryStock);

        System.out.println("Apple amount: " + stock.getAppleAmount());
        System.out.println("Pear amount: " + stock.getPearAmount());
        System.out.println("Cherry amount: " + stock.getCherryAmount());

        stock.sellApple(1);
        stock.sellPear(2);
        stock.sellCherry(7.8);

        System.out.println("Apple amount: " + stock.getAppleAmount());
        System.out.println("Pear amount: " + stock.getPearAmount());
        System.out.println("Cherry amount: " + stock.getCherryAmount());

    }
}
