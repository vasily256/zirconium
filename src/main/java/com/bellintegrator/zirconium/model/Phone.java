package com.bellintegrator.zirconium.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Номер телефона
 */
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PhoneSequence")
    @SequenceGenerator(name="PhoneSequence", sequenceName = "Phone_sequence", allocationSize = 1)
    private long id;

    @Version
    private int version;

    private String phone;

    @ManyToMany(mappedBy = "phone", fetch = FetchType.LAZY)
    private Set<Office> offices;

    protected Phone() {
        super();
    }

    public Phone(String phone) {
        this.phone = phone;
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
        return phone;
    }
}
