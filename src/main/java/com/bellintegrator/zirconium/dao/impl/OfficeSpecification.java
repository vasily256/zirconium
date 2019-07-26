package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfficeSpecification implements Specification<Office> {
    private final Office office;

    @Override
    public Predicate toPredicate(Root<Office> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {

        criteriaQuery.distinct(true);
        Join<Office, Phone> phoneJoin = root.join("phones", JoinType.LEFT);
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
            predicates.add(phoneJoin.get("phone").in(strPhones));
        }

        Boolean isActive = office.getIsActive();
        if (isActive != null) {
            predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
