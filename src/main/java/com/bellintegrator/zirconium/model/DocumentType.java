package com.bellintegrator.zirconium.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Вид документа
 */
@Entity
@Table(name = "Document_Type")
@NoArgsConstructor @Setter @Getter
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocumentTypeSequence")
    @SequenceGenerator(name="DocumentTypeSequence", sequenceName = "Document_Type_sequence", allocationSize = 1)
    private long id;

    private String code;

    private String name;

    @Override
    public String toString() {
        return "DocumentType{" +
                       "code='" + code + '\'' +
                       ", name='" + name + '\'' +
                       '}';
    }
}
