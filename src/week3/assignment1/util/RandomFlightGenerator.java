package week3.assignment1.util;

import week3.assignment1.flight.Flight;
import week3.assignment1.flight.LufthansaFlight;
import week3.assignment1.flight.PegasusFlight;
import week3.assignment1.flight.THYFlight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFlightGenerator {

    private static final List<String> cityList = List.of("Istanbul", "Ankara", "Izmir", "Konya", "Trabzon", "Adana", "Antalya",
            "Erzurum", "Kayseri", "Berlin", "Munih", "Paris", "Madrid", "Roma", "Floransa", "New York", "Dallas","Kahire");
    private static final Random random = new Random();


    public static Flight generateRandomFlight() {
        //randomly selecting flight route
        int fromIndex = random.nextInt(cityList.size());
        String fromCity = cityList.get(fromIndex);
        int toIndex = random.nextInt(cityList.size());
        while (toIndex == fromIndex) {  //making sure that fromCity and toCity are different
            toIndex = random.nextInt(cityList.size());
        }
        String toCity = cityList.get(toIndex);

        int flightCapacity = random.nextInt(101) + 50; //flight capacity is between 50 and 150
        double flightBasePrice = random.nextDouble() * 1000 + 150;

        //randomly creating departure and arrival time
        //departure and arrival time difference is at most 12 hours
        int days = random.nextInt(12);
        int hours = random.nextInt(12);
        int minutes = random.nextInt(60);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureDate = now.plusDays(days).plusHours(hours); //securing that flight time is after today
        LocalDateTime arrivalDate = departureDate.plusHours(hours).plusMinutes(minutes);

        //choosing one of 3 airlines to create new flight
        int airlines = random.nextInt(3);
        if(airlines == 0) {
            return new THYFlight(fromCity, toCity, departureDate, arrivalDate, flightCapacity, flightBasePrice);
        } else if(airlines == 1) {
            return new LufthansaFlight(fromCity, toCity, departureDate, arrivalDate, flightCapacity, flightBasePrice);
        }else {
            return new PegasusFlight(fromCity, toCity, departureDate, arrivalDate, flightCapacity, flightBasePrice);
        }
    }

    //shortcut for creating more than one random flight at once in other classes
    public static List<Flight> generateRandomFlightList(int numberOfFlights) {
        List<Flight> randomFlightList = new ArrayList<>();
        for (int i = 0; i < numberOfFlights; i++) {
            randomFlightList.add(generateRandomFlight());
        }
        return randomFlightList;
    }
}
