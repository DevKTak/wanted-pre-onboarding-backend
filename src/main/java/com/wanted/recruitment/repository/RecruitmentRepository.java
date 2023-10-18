package com.wanted.recruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wanted.recruitment.domain.Recruitment;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

	@Query("SELECT r FROM Recruitment r WHERE r.company.name "
		+ "LIKE %:search% OR r.position LIKE %:search% OR r.skill LIKE %:search%")
	List<Recruitment> findBySearchParam(String search);
}
