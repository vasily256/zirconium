package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.model.Organization;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OrganizationSpecification implements Specification<Organization> {
    private final Organization org;

    @Override
    public Predicate toPredicate(Root<Organization> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        String name = org.getName();
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        String inn = org.getInn();
        if (inn != null) {
            predicates.add(criteriaBuilder.like(root.get("inn"), inn + "%"));
        }

        Boolean isActive = org.getIsActive();
        if (isActive != null) {
            predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
