package com.umit.repository;

import com.umit.entity.Leave;
import com.umit.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    Optional<List<Leave>> findAllByEmployeeId(Long employeeId);

    Optional<List<Leave>> findAllByManagerIdAndStatus(Long managerId, EStatus eStatus);

    List<Leave> findAllByAuthId(Long authId);

}
