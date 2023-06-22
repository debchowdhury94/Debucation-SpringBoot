package com.deb.debucation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Struct;

@Entity
@Table(name = "Address")
@NoArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Address_Line",nullable = true)
    private String addressLine;

    @Column(name = "City", nullable = true)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(length = 10, nullable = false)
    private String pin;

    @OneToOne(mappedBy = "address")
    Student student;

    public Address(String addressLine, String city, String state, String pin) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.pin = pin;
    }
}
