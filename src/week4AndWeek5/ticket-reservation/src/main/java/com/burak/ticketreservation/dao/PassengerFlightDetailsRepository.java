package com.burak.ticketreservation.dao;

import com.burak.ticketreservation.entity.PassengerFlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerFlightDetailsRepository extends JpaRepository<PassengerFlightDetails, Integer> {

    public PassengerFlightDetails findByFlightIdAndPassengerId(int flightId, int passengerId);

    public List<PassengerFlightDetails> findAllByPassengerId(int passengerId);
}
