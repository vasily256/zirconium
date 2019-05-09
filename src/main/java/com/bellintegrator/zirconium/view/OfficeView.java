package com.bellintegrator.zirconium.view;

import com.bellintegrator.zirconium.view.validation.group.ListViews;
import com.bellintegrator.zirconium.view.validation.group.SaveView;
import com.bellintegrator.zirconium.view.validation.group.UpdateView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Офис
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {

    @NotNull(groups = UpdateView.class)
    private Long id;

    @NotNull(groups = {ListViews.class, SaveView.class})
    private Long orgId;

    @NotNull(groups = UpdateView.class)
    private String name;

    @NotNull(groups = UpdateView.class)
    private String address;

    private Set<String> phone;

    private Boolean isActive;

    public OfficeView(Long id, Long orgId, String name, String address,
                      Set<String> phone, Boolean isActive) {
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

    public Set<String> getPhone() {
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

    public void setPhone(Set<String> phone) {
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
