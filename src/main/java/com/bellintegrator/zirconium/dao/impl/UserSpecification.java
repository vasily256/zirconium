package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.model.User;
import com.bellintegrator.zirconium.model.Phone;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSpecification implements Specification<User> {
    private final User user;

    public UserSpecification(User user) {
        this.user = user;
    }

    @Override
    public Predicate toPredicate(Root<User> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
//
//        criteriaQuery.distinct(true);
//        Join<User, Phone> phone = root.join("phone", JoinType.LEFT);
//        List<Predicate> predicates = new ArrayList<>();
//
//        Long orgId = user.getOrgId();
//        predicates.add(criteriaBuilder.equal(root.get("orgId"), orgId));
//
//        String name = user.getName();
//        if (name != null) {
//            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
//        }
//
//        String address = user.getAddress();
//        if (address != null) {
//            predicates.add(criteriaBuilder.equal(root.get("address"), address));
//        }
//
//        Set<Phone> phones = user.getPhone();
//        if (phones != null) {
//            Set<String> strPhones = phones.stream()
//                                          .map(Phone::getPhone)
//                                          .collect(Collectors.toSet());
//            predicates.add(phone.get("phone").in(strPhones));
//        }
//
//        Boolean isActive = user.isActive();
//        if (isActive != null) {
//            predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
//        }
//
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        return null;
    }
}
