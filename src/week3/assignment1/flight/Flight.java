package week3.assignment1.flight;

import week3.assignment1.passenger.Passenger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Flight {
    private String fromCity;
    private String toCity;
    private List<Passenger> passengers;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private boolean[] seats;
    private final double baseTicketPrice;

    public Flight(String fromCity, String toCity, LocalDateTime departureDate, LocalDateTime arrivalDate, int seatCapacity, double price) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        passengers = new ArrayList<>();
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        seats = new boolean[seatCapacity];
        this.baseTicketPrice = price;
    }

    //printing plane seats with style:
    // _____ ___X__    X means seat is occupied, while _ means available
    // __X__ ______
    public void showSeats() {
        //each seat has a row number and letter(A,B,C,D,E,F,G,H,I,J) which make seat choice easier
        System.out.println("  ABCDE FGHIJ");
        int row = 1;
        System.out.print("0" + row + " ");
        for (int i = 0; i < seats.length; i++) {
            if(seats[i] == false) {
                System.out.print("_"); //seat is empty
            } else {
                System.out.print("X"); //seat is occupied
            }
            if((i + 1) % 10 == 0 && i != seats.length-1) {
                row++;
                if (row < 10) {
                    System.out.print("\n0" + row + " ");
                } else {
                    System.out.print("\n" + row + " ");
                }

            } else if ((i + 1) % 5 == 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    //converting seat code(11 A) into array index for using it in seats array
    public int convertSeatNumberToIndex(String seat) {
        seat = seat.toUpperCase();
        try {
            String[] seatArr = seat.split(" ");
            int seatIndex = ((Integer.parseInt(seatArr[0]) - 1) * 10) + Character.valueOf(seatArr[1].charAt(0)) - 65;
            return seatIndex;
        } catch (ArrayIndexOutOfBoundsException exc) { //validating seat code
            System.out.println("Error! Seat number format should be seat number followed by seat letter. Example: 11 A");
            return -1;
        }
    }

    public boolean isSeatAvailable(int seatNumber) {
        try {
            return !seats[seatNumber];
        } catch (ArrayIndexOutOfBoundsException exc) {
            return false;
        }
    }

    public double takeTicket(Passenger passenger, int seatNumber) {
        passengers.add(passenger);
        seats[seatNumber] = true;
        return calculateTicketPrice(passenger);
    }

    public double calculateTicketPrice(Passenger passenger) {
        return baseTicketPrice;
    }

    public void returnTicket(Passenger passenger, int seatNumber) {
        passengers.remove(passenger);
        seats[seatNumber] = false;
    }

    public boolean isFlightFull() {
        for (int i = 0; i < seats.length; i++) {
            if(seats[i] == false) {
                return false;
            }
        }
        return true;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public boolean[] getSeats() {
        return seats;
    }

    public void setSeats(boolean[] seats) {
        this.seats = seats;
    }

    public double getBaseTicketPrice() {
        return baseTicketPrice;
    }

    //used for printing flight object in the console
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "  " + fromCity + " ---> " + toCity + "  "
               + formatLocalDateTime() + String.format("  $%.2f", baseTicketPrice);
    }

    private String formatLocalDateTime() {
        //if departure and arrival days are same, do not display the same date twice, just show the time as hours and minutes
        if (departureDate.getDayOfYear() == arrivalDate.getDayOfYear()) {
            return departureDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm")) + " - " +
                    arrivalDate.format(DateTimeFormatter.ofPattern("kk:mm"));
        } else {
            return departureDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm")) + " - " +
                    arrivalDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm"));
        }
    }

}
