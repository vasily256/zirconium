package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {
    private final User user;

    public UserSpecification(User user) {
        this.user = user;
    }

    @Override
    public Predicate toPredicate(Root<User> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {

        Join<User, Country> countryJoin = root.join("country", JoinType.LEFT);
        Join<User, Document> documentJoin = root.join("document", JoinType.LEFT);
        Join<Document, DocumentType> docTypeJoin = documentJoin.join("documentType", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        Long officeId = user.getOfficeId();
        predicates.add(criteriaBuilder.equal(root.get("officeId"), officeId));

        String firstName = user.getFirstName();
        if (firstName != null) {
            predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
        }

        String secondName = user.getSecondName();
        if (secondName != null) {
            predicates.add(criteriaBuilder.equal(root.get("secondName"), secondName));
        }

        String middleName = user.getMiddleName();
        if (middleName != null) {
            predicates.add(criteriaBuilder.equal(root.get("middleName"), middleName));
        }

        String position = user.getPosition();
        if (position != null) {
            predicates.add(criteriaBuilder.like(root.get("position"), "%" + position + "%"));
        }

        if (user.getCountry() != null && user.getCountry().getCode() != null) {
            String countryCode = user.getCountry().getCode();
            predicates.add(criteriaBuilder.equal(countryJoin.get("code"), countryCode));

        }

        if (user.getDocument() != null
            && user.getDocument().getDocumentType() != null
            && user.getDocument().getDocumentType().getCode() != null) {

            String docTypeCode = user.getDocument().getDocumentType().getCode();

            predicates.add(criteriaBuilder.equal(docTypeJoin.get("code"), docTypeCode));

        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
