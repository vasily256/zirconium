package com.bellintegrator.zirconium.dao;

import com.bellintegrator.zirconium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>,
                                        JpaSpecificationExecutor<User> {
}
