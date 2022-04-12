package com.burak.ticketreservation.dao;

import com.burak.ticketreservation.entity.Flight;
import com.burak.ticketreservation.entity.LufthansaFlight;
import com.burak.ticketreservation.entity.PegasusFlight;
import com.burak.ticketreservation.entity.THYFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FlightRepository<T extends Flight> extends JpaRepository<T, Integer> {

    //these three methods are for getting flights by airline company name instead of getting all flights at once

    @Query("from thy_flight")
    public List<THYFlight> findAllTHYFlights();

    @Query("from pegasus_flight")
    public List<PegasusFlight> findAllPegasusFlights();

    @Query("from lufthansa_flight")
    public List<LufthansaFlight> findAllLufthansaFlights();
}
