package week2.assignment5;

import java.util.Scanner;

/**
 * Sinema bileti satışı:
 * ücret hesaplama: 18 yaş altı için %10 indirimli.
 * 18 ve 25 yaş arası % 5 indirim.
 * Korku filmi seçilmişse ekstra % 10 indirim.
 */


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UserInterface ui = new UserInterface(scan);
        ui.start();
        scan.close();
    }
}
