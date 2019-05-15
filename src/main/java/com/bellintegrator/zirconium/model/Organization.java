package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Организация
 */
@Entity
@NoArgsConstructor @Setter @Getter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrganizationSequence")
    @SequenceGenerator(name="OrganizationSequence", sequenceName = "Organization_sequence", allocationSize = 1)
    private Long id;

    @Version
    private Integer version;

    private String name;

    @Column(name = "full_name")
    private String fullName;

    private String inn;

    private String kpp;

    private String address;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "Organization_Phone",
            joinColumns = @JoinColumn(name = "org_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id")
    )
    private Set<Phone> phones;

    @Column(name = "is_active")
    private Boolean isActive;


    @PrePersist
    void preInsert() {
        if (isActive == null) {
            isActive = true;
        }
    }
}
