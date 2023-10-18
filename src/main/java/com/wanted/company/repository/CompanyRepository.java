package com.wanted.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wanted.company.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
