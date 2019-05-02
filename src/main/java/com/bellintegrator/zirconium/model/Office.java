package com.bellintegrator.zirconium.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OfficeSequence")
    @SequenceGenerator(name="OfficeSequence", sequenceName = "Office_sequence", allocationSize=1)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "org_id")
    private Long orgId;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
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
    private Boolean isActive;

    protected Office() {
        super();
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
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

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Office{" +
                       "id=" + id +
                       ", version=" + version +
                       ", orgId=" + orgId +
                       ", name='" + name + '\'' +
                       ", address=" + address +
                       ", phone=" + phone +
                       ", isActive=" + isActive +
                       '}';
    }
}
