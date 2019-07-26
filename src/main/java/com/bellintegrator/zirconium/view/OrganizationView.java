package com.bellintegrator.zirconium.view;

import com.bellintegrator.zirconium.view.validation.group.SaveView;
import com.bellintegrator.zirconium.view.validation.group.UpdateView;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Организация
 */
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {

    @NotNull(groups = {UpdateView.class})
    private Long id;

    @NotNull(groups = {SaveView.class, UpdateView.class})
    private String name;

    @NotNull(groups = {SaveView.class, UpdateView.class})
    private String fullName;

    @NotNull(groups = {SaveView.class, UpdateView.class})
    private String inn;

    @NotNull(groups = {SaveView.class, UpdateView.class})
    private String kpp;

    @NotNull(groups = {SaveView.class, UpdateView.class})
    private String address;

    private Set<String> phone;

    private Boolean isActive;
}
