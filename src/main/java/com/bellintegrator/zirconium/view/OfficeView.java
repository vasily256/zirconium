package com.bellintegrator.zirconium.view;

import com.bellintegrator.zirconium.view.validation.group.ListViews;
import com.bellintegrator.zirconium.view.validation.group.SaveView;
import com.bellintegrator.zirconium.view.validation.group.UpdateView;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Офис
 */
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
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
}
