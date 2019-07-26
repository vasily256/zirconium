package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import java.util.Set;

/**
 * Пользователь
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
    @SequenceGenerator(name="UserSequence", sequenceName = "User_sequence", allocationSize = 1)
    private Long id;

    @Version
    private Integer version;

    private Long officeId;

    private String firstName;

    private String secondName;

    private String middleName;

    private String position;

    @ManyToMany(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinTable(
        name = "User_Phone",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "phone_id")
    )
    private Set<Phone> phones;

    @OneToOne(
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
}
