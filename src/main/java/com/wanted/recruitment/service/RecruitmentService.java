package com.wanted.recruitment.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.company.domain.Company;
import com.wanted.company.repository.CompanyRepository;
import com.wanted.recruitment.domain.Recruitment;
import com.wanted.recruitment.dto.CreateRecruitmentRequest;
import com.wanted.recruitment.dto.FindRecruitmentDetailResponse;
import com.wanted.recruitment.dto.FindRecruitmentResponse;
import com.wanted.recruitment.dto.UpdateRecruitmentRequest;
import com.wanted.recruitment.repository.RecruitmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentService {

	private final RecruitmentRepository recruitmentRepository;

	private final CompanyRepository companyRepository;

	@Transactional
	public void create(CreateRecruitmentRequest dto) {
		Company company = companyRepository.findById(dto.getCompanyId())
			.orElseThrow(() -> new NoSuchElementException("회사 ID 값에 해당하는 회사를 찾을 수 없습니다."));

		recruitmentRepository.save(Recruitment.builder()
			.name(company.getName())
			.position(dto.getPosition())
			.compensation(dto.getCompensation())
			.content(dto.getContent())
			.skill(dto.getSkill())
			.company(company)
			.build());
	}

	@Transactional
	public void update(UpdateRecruitmentRequest dto, Long recruitmentId) {
		Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
			.orElseThrow(() -> new NoSuchElementException("채용공고 ID 값에 해당하는 채용공고를 찾을 수 없습니다."));

		recruitment.update(dto.getPosition(), dto.getCompensation(), dto.getContent(), dto.getSkill());
	}

	@Transactional
	public void deleteAll() {
		recruitmentRepository.deleteAll();
	}

	public List<FindRecruitmentResponse> findAll() {
		List<Recruitment> recruitments = recruitmentRepository.findAll();

		if (recruitments.size() == 0) {
			throw new NoSuchElementException("조회된 결과가 없습니다.");
		}
		return recruitments.stream()
			.map(r -> r.toDto(r))
			.collect(Collectors.toList());
	}

	public FindRecruitmentDetailResponse findById(Long recruitmentId) {
		Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
			.orElseThrow(() -> new NoSuchElementException("채용공고 ID 값에 해당하는 채용공고를 찾을 수 없습니다."));

		Long companyId = recruitment.getCompany().getId();
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new NoSuchElementException("회사 ID 값에 해당하는 회사를 찾을 수 없습니다."));

		List<Recruitment> recruitments = company.getRecruitments();

		if (recruitments == null) {
			throw new NoSuchElementException("조회된 결과가 없습니다.");
		}
		List<Long> recruitmentIds = recruitments.stream()
			.map(Recruitment::getId)
			.filter(rId -> !Objects.equals(rId, recruitmentId))
			.collect(Collectors.toList());

		return new FindRecruitmentDetailResponse(recruitment.getId(), recruitment.getName(), company.getName(),
			company.getNation(), company.getArea(), recruitment.getPosition(), recruitment.getCompensation(),
			recruitment.getSkill(), recruitment.getContent(), recruitmentIds);
	}

	public List<FindRecruitmentResponse> findBySearchParam(String search) {
		List<Recruitment> recruitments = recruitmentRepository.findBySearchParam(search);

		if (recruitments == null) {
			throw new NoSuchElementException("조회된 결과가 없습니다.");
		}
		return recruitments.stream()
			.map(r -> r.toDto(r))
			.collect(Collectors.toList());
	}
}
