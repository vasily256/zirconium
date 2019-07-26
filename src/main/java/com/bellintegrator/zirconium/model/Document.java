package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;
import java.time.LocalDate;

/**
 * Документ
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Document {

    @Id
    private Long userId;

    @Version
    private Integer version;

    private String docNumber;

    private LocalDate docDate;

    private Boolean isIdentified;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    @PrePersist
    void preInsert() {
        if (isIdentified == null) {
            isIdentified = true;
        }
    }
}
