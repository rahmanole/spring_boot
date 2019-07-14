package com.security.bootsecurity.dao;

import com.security.bootsecurity.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriviligeRepository extends JpaRepository<Privilege,Long> {
}
