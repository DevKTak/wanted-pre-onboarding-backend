package com.wanted.recruitment.dto;

import java.util.List;

public record FindRecruitmentDetailResponse(Long recruitmentId, String recruitmentName, String companyName,
											String nation, String area,
											String position, int compensation, String skill, String content,
											List<Long> recruitmentIds) {
}
