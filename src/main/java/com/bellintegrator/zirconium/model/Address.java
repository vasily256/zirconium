package com.bellintegrator.zirconium.model;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AddressSequence")
    @SequenceGenerator(name="AddressSequence", sequenceName = "Address_sequence", allocationSize = 1)
    private long id;

    @Version
    private Integer version;

    private String address;

    protected Address() {
        super();
    }

    public Address(String address) {
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

    @Override
    public String toString() {
        return "'" + address + "'";
    }
}
