package com.burak.ticketreservation.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table
@Entity(name = "passenger")
@Getter
@Setter
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="flight_passenger_details",
            joinColumns = @JoinColumn(name="passenger_id"),
            inverseJoinColumns = @JoinColumn(name="flight_id"))
    private List<Flight> flights;


}
