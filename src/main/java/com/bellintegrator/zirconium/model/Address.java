package com.bellintegrator.zirconium.model;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Address_sequence")
    private Long id;

    @Version
    private Integer version;

    private String address;

    public Address() {

    }

    public Address(Integer version, String address) {
        this.version = version;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
