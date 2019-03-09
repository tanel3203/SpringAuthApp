package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column(name="user_name")
    private String userName;

    @Column(name="email")
    private String email;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name="address")
    private String address;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name="country")
    private String country;

    public Client() {}

    public Client(String firstName, String lastName, String userName, String email, String address, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format(
                "Client[id=%d, firstName='%s', lastName='%s', userName='%s', email='%s', address='%s', country='%s']",
                id, firstName, lastName, userName, email, address, country);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}