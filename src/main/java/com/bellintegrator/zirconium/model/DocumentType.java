package com.bellintegrator.zirconium.model;

import javax.persistence.*;

@Entity
@Table(name = "Document_Type")
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Document_Type_sequence")
    private Long id;

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
