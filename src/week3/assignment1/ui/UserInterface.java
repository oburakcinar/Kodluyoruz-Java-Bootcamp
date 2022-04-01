package week3.assignment1.ui;

import week3.assignment1.flight.Flight;
import week3.assignment1.flight.IAbroadFoodChoice;
import week3.assignment1.flight.IBusinessClass;
import week3.assignment1.passenger.Passenger;
import week3.assignment1.service.TicketService;
import week3.assignment1.util.FlightDestinationComparator;
import week3.assignment1.util.FlightPriceComparator;
import week3.assignment1.util.RandomFlightGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private List<Passenger> registeredPassengers;
    private TicketService ticketService;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        registeredPassengers = new ArrayList<>();
        ticketService = new TicketService();
    }

    public void start() {
        List<Flight> flights = RandomFlightGenerator.generateRandomFlightList(10);

        System.out.println("Welcome to Flight Booking Application!\n");

        mainLoop:
        while (true) {
            showFlightList(flights);
            String command = scanner.next();
            Flight flight = null;

            if (command.equalsIgnoreCase("r")) { //return flight operation
                Passenger passenger = null;
                //entering passenger id will get the passenger and all his/her flight tickets
                while (passenger == null) {
                    System.out.println("Enter your id or press 0 to go back to main menu");
                    long passengerId = scanner.nextLong();
                    if(passengerId == 0) {
                        continue mainLoop;
                    }
                    passenger = getExistingPassenger(passengerId);
                    if (passenger == null) { //if the passenger has not bought a ticket yet, return option is unavailable
                        System.out.println("Error! No passenger is found with the id.");
                    }
                }
                if (passenger.getAllPurchasedFlights().size() == 0) {
                    System.out.println("No flight tickets purchased yet. Returning back to main menu...\n");
                    continue mainLoop;
                }
                showPassengerPurchasedFlights(passenger);
                Flight returnedFlight = getFlightToBeReturned(passenger);
                ticketService.returnTicket(returnedFlight, passenger); //executing return ticket
                continue;
            } else if (command.equalsIgnoreCase("p")) { //sorting flight list by ascending ticket price
                Collections.sort(flights, new FlightPriceComparator());
                continue;
            } else if (command.equalsIgnoreCase("d")) { //sorting flight list by destination
                Collections.sort(flights, new FlightDestinationComparator());
                continue;
            }else if (command.equalsIgnoreCase("e")) { //exiting the application
                System.exit(0);
            } else {
                try {
                    flight = flights.get(Integer.parseInt(command));
                } catch (IndexOutOfBoundsException exc) {
                    System.out.println("Invalid command!\n"); //if flight number does not exist, prompting user to enter flight number again
                    continue;
                }
            }
            if (flight.isFlightFull()) { //if no seat available, going back to main menu
                System.out.println("Unfortunately, this flight is full. Please select another one.\n");
                continue;
            }

            Passenger passenger = getPassenger();
            flight.showSeats(); //showing user available seat options
            int seatNumber = selectSeat(flight);
            askBusinessClass(flight, passenger); //if flight implements businessclass interface, user is asked for business class upgrade
            abroadFlightFoodChoice(flight); //if flight implements abroadfood interface, food options are shown
            ticketService.takeTicketForPassenger(flight, passenger, seatNumber); //executing take ticket
            registeredPassengers.add(passenger); //storing passenger object for return ticket or buying ticket from another flight
            passenger.setCurrentFlightBusinessClass(false);
        }
    }

    //main menu of the app
    private void showFlightList(List<Flight> flights) {
        System.out.println("Flight List:");

        int index = 0;
        for (Flight flight : flights) {
            System.out.println(index + ") " + flight);
            index++;
        }
        System.out.println("\nr) return ticket");
        System.out.println("p) sort flights by price");
        System.out.println("d) sort flights by destination");
        System.out.println("e) exit\n");
    }

    //if passenger is registered to the app, returns the passenger, so there is no need to enter the name and age again
    //if not, creating new passenger with user inputs
    private Passenger getPassenger() {
        System.out.println("Enter your id:");
        long passengerId = scanner.nextLong();
        Passenger passenger = getExistingPassenger(passengerId);
        if(passenger != null) {
            System.out.println("Passenger is already registered to the system. Go ahead!");
            return passenger;
        }
        //if passenger is not exist, create new passenger
        System.out.println("Enter your name:");
        String passengerName = scanner.nextLine();
        passengerName = scanner.nextLine();

        System.out.println("Enter your age:");
        int passengerAge = scanner.nextInt();

        passenger = new Passenger(passengerId, passengerName, passengerAge);
        return passenger;
    }

    //finding registered passenger by id
    private Passenger getExistingPassenger(long passengerId) {
        for (Passenger passenger : registeredPassengers) {
            if (passenger.getId() == passengerId) {
                return passenger;
            }
        }
        return null;
    }

    //used for getting selected number from user input
    private int selectSeat(Flight flight) {
        System.out.println("Select your seat. Seats with X sign mean occupied. Please select a seat with _ sign." +
                " Input format should be seat number followed by seat letter. Example: 11 A");
        String seat = scanner.nextLine();
        seat = scanner.nextLine();
        int seatNumber = flight.convertSeatNumberToIndex(seat);
        while (!flight.isSeatAvailable(seatNumber)) { //if the seat occupied, the user has to select another number
            if (seatNumber != -1) {
                System.out.println("Sorry, this seat is occupied! Please choose another one.");
            }
            seat = scanner.nextLine();
            //seat = scanner.nextLine();
            seatNumber = flight.convertSeatNumberToIndex(seat);
        }
        System.out.println("Seat number is successfully selected.");
        return seatNumber;
    }

    private void askBusinessClass(Flight flight, Passenger passenger) {
        if (flight instanceof IBusinessClass) {
            System.out.println("Do you want to upgrade to your flight to business class? Y for yes, N for no");
            String businessChoice = scanner.next();
            if (businessChoice.equalsIgnoreCase("Y")) {
                passenger.setCurrentFlightBusinessClass(true);
                System.out.println("Success! Your flight is now business class.");
            }
        }
    }

    private void abroadFlightFoodChoice(Flight flight) {
        if (flight instanceof IAbroadFoodChoice) {
            ((IAbroadFoodChoice) flight).foodChoice();
        }
    }

    //printing all the flights passenger has bought
    private void showPassengerPurchasedFlights(Passenger passenger) {
        int index = 0;
        for (Flight flight : passenger.getAllPurchasedFlights()) {
            System.out.println(index + ") " + flight);
            index++;
        }
    }

    //returning flight that the user has selected to return it
    private Flight getFlightToBeReturned(Passenger passenger) {
        Flight flight = null;
        while (flight == null) {
            System.out.println("\nSelect the flight ticket you would like to return:");
            int flightIndex = scanner.nextInt();
            try {
                flight = passenger.getPurchasedFlights().get(flightIndex).getFlight();
            } catch (IndexOutOfBoundsException exc) {
                System.out.println("Invalid flight number");
            }
        }
        return flight;
    }
}
