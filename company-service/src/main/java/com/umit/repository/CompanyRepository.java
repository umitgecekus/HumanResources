package com.umit.repository;

import com.umit.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findOptionalByName(Long id);

    Optional<Company> findCompanyByManagerId(Long id);

}
