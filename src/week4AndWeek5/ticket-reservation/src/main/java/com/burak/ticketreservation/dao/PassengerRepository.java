package com.burak.ticketreservation.dao;

import com.burak.ticketreservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
