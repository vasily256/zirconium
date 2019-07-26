package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Вид документа
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocumentTypeSequence")
    @SequenceGenerator(name="DocumentTypeSequence", sequenceName = "Document_Type_sequence", allocationSize = 1)
    private long id;

    private String code;

    private String name;
}
