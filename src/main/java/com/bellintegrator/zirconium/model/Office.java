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

    protected Office() {
        super();
    }

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
