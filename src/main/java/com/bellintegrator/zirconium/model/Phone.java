package com.bellintegrator.zirconium.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

/**
 * Номер телефона
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
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
}
