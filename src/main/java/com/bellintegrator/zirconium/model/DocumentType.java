package com.bellintegrator.zirconium.model;

import javax.persistence.*;

/**
 * Вид документа
 */
@Entity
@Table(name = "Document_Type")
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocumentTypeSequence")
    @SequenceGenerator(name="DocumentTypeSequence", sequenceName = "Document_Type_sequence", allocationSize = 1)
    private long id;

    private String code;

    private String name;

    protected DocumentType() {
        super();
    }

    public DocumentType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
