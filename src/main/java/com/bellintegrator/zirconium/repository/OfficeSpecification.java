package com.bellintegrator.zirconium.repository;

import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class OfficeSpecification implements Specification<Office> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

        criteriaQuery.distinct(true);

        if (value == null) {
            return null;
        }

        if (operator == Operator.EQUAL) {
            return criteriaBuilder.equal(root.get(key), value);
        } else if (operator == Operator.IN) {
            Join<Office, Phone> phone = root.join("phone");
            return phone.get("phone").in(value);
        } else if (value instanceof String && operator == Operator.LIKE) {
            return criteriaBuilder.like(root.get(key), "%" + value + "%");
        } else {
            return null;
        }
    }
}
