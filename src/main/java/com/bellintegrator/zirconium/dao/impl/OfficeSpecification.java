package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OfficeSpecification implements Specification<Office> {
    private final Office office;

    public OfficeSpecification(Office office) {
        this.office = office;
    }

    @Override
    public Predicate toPredicate(Root<Office> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {

        criteriaQuery.distinct(true);
        Join<Office, Phone> phone = root.join("phones", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();

        Long orgId = office.getOrgId();
        predicates.add(criteriaBuilder.equal(root.get("orgId"), orgId));

        String name = office.getName();
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        String address = office.getAddress();
        if (address != null) {
            predicates.add(criteriaBuilder.equal(root.get("address"), address));
        }

        Set<Phone> phones = office.getPhones();
        if (phones != null) {
            Set<String> strPhones = phones.stream()
                                          .map(Phone::getPhone)
                                          .collect(Collectors.toSet());
            predicates.add(phone.get("phone").in(strPhones));
        }

        Boolean isActive = office.getIsActive();
        if (isActive != null) {
            predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
