package com.bellintegrator.zirconium.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {

    private Long id;
    private Long orgId;
    private String name;
    private String address;
    private List<String> phone;
    private Boolean isActive;

    public OfficeView(Long id, Long orgId, String name,
                      String address, List<String> phone, Boolean isActive) {
        this.id = id;
        this.orgId = orgId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OfficeView() {
        super();
    }

    public Long getId() {
        return id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getPhone() {
        return phone;
    }

    @JsonProperty("isActive") // предотвращение произвольного переименования поля в ответе
    public Boolean isActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "OfficeView{" +
                       "id=" + id +
                       ", orgId=" + orgId +
                       ", name='" + name + '\'' +
                       ", address='" + address + '\'' +
                       ", phone=" + phone +
                       ", isActive=" + isActive +
                       '}';
    }
}
