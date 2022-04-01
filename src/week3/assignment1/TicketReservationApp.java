package week3.assignment1;

import week3.assignment1.ui.UserInterface;

import java.util.Scanner;

public class TicketReservationApp {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UserInterface ui = new UserInterface(scan);
        ui.start();
    }

}
