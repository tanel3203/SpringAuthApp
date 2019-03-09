package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name="iso3_value")
    private String iso3Value;

    @NotNull
    @Column(name="name")
    private String name;

    public Country() {
    }

    public Country(String iso3Value, String name) {
        this.iso3Value = iso3Value;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIso3Value() {
        return iso3Value;
    }

    public void setIso3Value(String iso3Value) {
        this.iso3Value = iso3Value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
