package com.wanted.recruitment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateRecruitmentRequest {

	@NotNull(message = "회사 ID를 입력해주세요.")
	private Long companyId;

	@NotBlank(message = "채용포지션을 입력해주세요.")
	private String position;

	@NotNull(message = "채용보상금을 입력해주세요.")
	private int compensation;

	@NotBlank(message = "채용내용을 입력해주세요.")
	private String content;

	@NotBlank(message = "사용기술을 입력해주세요.")
	private String skill;
}
