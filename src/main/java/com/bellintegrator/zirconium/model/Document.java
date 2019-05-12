package com.bellintegrator.zirconium.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Документ
 */
@Entity
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

    protected Document() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "Document{" +
                       "userId=" + userId +
                       ", version=" + version +
                       ", docNumber='" + docNumber + '\'' +
                       ", docDate=" + docDate +
                       ", isIdentified=" + isIdentified +
                       ", user=" + user +
                       ", documentType=" + documentType +
                       '}';
    }
}
