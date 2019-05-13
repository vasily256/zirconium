package com.bellintegrator.zirconium.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Номер телефона
 */
@Entity
@NoArgsConstructor @Setter @Getter
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PhoneSequence")
    @SequenceGenerator(name="PhoneSequence", sequenceName = "Phone_sequence", allocationSize = 1)
    private long id;

    @Version
    private int version;

    private String phone;

    @ManyToMany(mappedBy = "phones", fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    private Set<Office> offices;


    public Phone(String phone) {
        this.phone = phone;
    }

    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

    @Override
    public String toString() {
        return phone;
    }
}
