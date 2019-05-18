package com.bellintegrator.zirconium.view;

import com.bellintegrator.zirconium.view.validation.group.ListViews;
import com.bellintegrator.zirconium.view.validation.group.SaveView;
import com.bellintegrator.zirconium.view.validation.group.UpdateView;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor @Setter @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView {

    @NotNull(groups = UpdateView.class)
    private Long id;

    @NotNull(groups = {ListViews.class, SaveView.class})
    private Long officeId;

    @NotNull(groups = {UpdateView.class, SaveView.class})
    private String firstName;

    private String secondName;

    private String middleName;

    @NotNull(groups = {UpdateView.class, SaveView.class})
    private String position;

    private Set<String> phone;
    private String docCode;
    private String docName;
    private String docNumber;
    private LocalDate docDate;
    private String citizenshipName;
    private String citizenshipCode;
    private Boolean isIdentified;
}
