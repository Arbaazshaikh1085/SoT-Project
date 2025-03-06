package com.accenture.Railway_Ticketing_System.ticket;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table
public class Ticket {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id;
    private String username;
    private String startStation;
    private String endStation;
    private String payment;
    private int fare;
    private LocalDate date;
    private String paymentType;

    public Ticket() {
    }

    public Ticket(String username,
                  String startStation,
                  String endStation,
                  String payment,
                  int fare,
                  LocalDate date,
                  String paymentType) {
        this.username = username;
        this.startStation = startStation;
        this.endStation = endStation;
        this.payment = payment;
        this.fare = fare;
        this.date = date;
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", payment status is '" + payment + '\'' +
                ", fare=" + fare +
                ", date=" + date +
                '}';
    }
}
