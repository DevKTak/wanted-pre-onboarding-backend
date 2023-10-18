package com.wanted.recruitment.domain;

import com.wanted.company.domain.Company;
import com.wanted.recruitment.dto.FindRecruitmentResponse;
import com.wanted.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Recruitment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name; // 채용공고명 (예시에 없는 필드명)

	private String position;

	private int compensation;

	private String content;

	private String skill;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@OneToOne(mappedBy = "recruitment", fetch = FetchType.LAZY)
	private User user;

	public Recruitment update(String position, int compensation, String content, String skill) {
		this.position = position;
		this.compensation = compensation;
		this.content = content;
		this.skill = skill;

		return this;
	}

	public FindRecruitmentResponse toDto(Recruitment recruitment) {
		Company company = recruitment.getCompany();

		return new FindRecruitmentResponse(recruitment.getId(), recruitment.getName(), company.getName(),
			company.getNation(),
			company.getArea(), recruitment.getPosition(), recruitment.getCompensation(), recruitment.getSkill());
	}
}
