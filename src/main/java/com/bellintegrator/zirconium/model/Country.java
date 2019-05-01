package com.bellintegrator.zirconium.model;

import javax.persistence.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Country_sequence")
    private long id;

    private String code;

    private String name;

    protected Country() {
        super();
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
