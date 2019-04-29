package com.bellintegrator.zirconium.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Office_sequence")
    private long id;

    @Version
    private int version;

    @Column(name = "org_id")
    private long orgId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "Office_Phone",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id")
    )
    private List<Phone> phone;

    @Column(name = "is_active")
    private boolean isActive;
}
