package com.bellintegrator.zirconium.model;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Phone_sequence")
    private long id;

    @Version
    private int version;

    private String phone;
}
