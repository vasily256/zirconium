package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import java.util.Set;

/**
 * Офис
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OfficeSequence")
    @SequenceGenerator(name="OfficeSequence", sequenceName = "Office_sequence", allocationSize = 1)
    private Long id;

    @Version
    private Integer version;

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

    private Boolean isActive;


    @PrePersist
    void preInsert() {
        if (isActive == null) {
            isActive = true;
        }
    }
}
