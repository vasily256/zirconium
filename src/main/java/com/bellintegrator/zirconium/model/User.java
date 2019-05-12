package com.bellintegrator.zirconium.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Пользователь
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
    @SequenceGenerator(name="UserSequence", sequenceName = "User_sequence", allocationSize = 1)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "office_id")
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

    protected User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                       "id=" + id +
                       ", version=" + version +
                       ", officeId=" + officeId +
                       ", firstName='" + firstName + '\'' +
                       ", secondName='" + secondName + '\'' +
                       ", middleName='" + middleName + '\'' +
                       ", position='" + position + '\'' +
                       ", phones=" + phones +
                       ", document=" + document +
                       ", country=" + country +
                       '}';
    }
}
