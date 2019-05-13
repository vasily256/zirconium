package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Офис
 */
@Entity
@NoArgsConstructor @Setter @Getter
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OfficeSequence")
    @SequenceGenerator(name="OfficeSequence", sequenceName = "Office_sequence", allocationSize = 1)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "org_id")
    private Long orgId;

    private String name;

    private String address;

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
