package com.bellintegrator.zirconium.dao;

import com.bellintegrator.zirconium.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizationRepository extends JpaRepository<Organization, Long>,
                                                JpaSpecificationExecutor<Organization> {
}
