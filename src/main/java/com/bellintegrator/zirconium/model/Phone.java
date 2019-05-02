package com.bellintegrator.zirconium.model;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PhoneSequence")
    @SequenceGenerator(name="PhoneSequence", sequenceName = "Phone_sequence", allocationSize=1)
    private long id;

    @Version
    private int version;

    private String phone;

    protected Phone() {
        super();
    }

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\'" + phone + '\'';
    }
}
