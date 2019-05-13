package com.bellintegrator.zirconium.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

/**
 * Документ
 */
@Entity
@NoArgsConstructor @Setter @Getter
public class Document {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Version
    private Integer version;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "doc_date")
    private Date docDate;

    @Column(name = "is_identified")
    private Boolean isIdentified;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;
}
