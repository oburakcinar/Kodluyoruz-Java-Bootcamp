package week2.assignment1;

import java.util.Scanner;

/**
 * Manav dükkanı açmak isteyen bir adam hal'den bir miktar elma, bir miktar armut, bir miktar kiraz alacaktır.
 * Mallar dükkana gelmiştir.
 * Kullanıcı bu malları ayrı odalarda saklayacaktır.
 * Her bir odadaki elma, armut ve kirazın kg cinsinden değerini bulalım.
 *
 *
 */

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Fruit apple = new Apple(75.75, 3.8);
        Fruit pear = new Pear(45, 5.2);
        Fruit cherry = new Cherry(24.6, 9.95);

        Grocery grocery = new Grocery();
        grocery.addMultipleFruits(apple, pear, cherry);

        UserInterface ui = new UserInterface(scan, grocery);
        ui.start();

        scan.close();
    }
}
