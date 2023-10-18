package com.wanted.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wanted.recruitment.domain.Recruitment;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
