package com.umit.repository;

import com.umit.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findOptionalById(Long managerId);

    Optional<Manager> findByAuthId(Long authId);
}
