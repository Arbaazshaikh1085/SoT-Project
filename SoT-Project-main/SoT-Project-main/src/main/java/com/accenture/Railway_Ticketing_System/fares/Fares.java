package com.accenture.Railway_Ticketing_System.fares;

import jakarta.persistence.*;

@Entity
@Table(name = "fares")
public class Fares {

    @Id
    @Column(name = "id") // Assuming there's an 'id' column as the primary key
    private Long id;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "fare")
    private Double fare;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
}