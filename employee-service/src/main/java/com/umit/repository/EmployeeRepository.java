package com.umit.repository;

import com.umit.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findOptionalById(Long id);

    Optional<Employee> findOptionalByIdentityNumber(String identityNumber);

    List<Employee> findByManagerId(Long managerId);

    Optional<Employee> findByAuthId(Long authId);

}
