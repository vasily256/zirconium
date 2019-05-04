package com.bellintegrator.zirconium.repository;

import com.bellintegrator.zirconium.model.Office;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OfficeSpecification implements Specification<Office> {

    private String key;
    private Operator operator;
    private Object value;

    public OfficeSpecification(String key, Operator operator, Object value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Office> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {

        if (value == null) {
            return null;
        }

        if (operator == Operator.EQUAL) {
            return criteriaBuilder.equal(root.get(key), value);
        } else if (operator == Operator.LIKE) {
            return criteriaBuilder.like(root.get(key), "%" + value + "%");
        } else {
            return null;
        }
    }
}
