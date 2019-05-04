package com.bellintegrator.zirconium.repository;

import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import com.bellintegrator.zirconium.view.OfficeView;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class OfficeSpecification implements Specification<Office> {
    private final OfficeView officeView;

    public OfficeSpecification(OfficeView officeView) {
        this.officeView = officeView;
    }

    @Override
    public Predicate toPredicate(Root<Office> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {

        criteriaQuery.distinct(true);
        Join<Office, Phone> phone = root.join("phone");
        List<Predicate> predicates = new ArrayList<>();

        Long orgId = officeView.getOrgId();
        predicates.add(criteriaBuilder.equal(root.get("orgId"), orgId));

        Boolean isActive = officeView.isActive();
        if (isActive != null) {
            predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
        }

        List<String> phones = officeView.getPhone();
        if (phones != null) {
            predicates.add(phone.get("phone").in(phones));
        }

        String name = officeView.getName();
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
