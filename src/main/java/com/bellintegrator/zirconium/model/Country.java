package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Страна мира
 */
@Entity
@NoArgsConstructor @Setter @Getter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CountrySequence")
    @SequenceGenerator(name="CountrySequence", sequenceName = "Country_sequence", allocationSize = 1)
    private long id;

    private String code;

    private String name;
}
