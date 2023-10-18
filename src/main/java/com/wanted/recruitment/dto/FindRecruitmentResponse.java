package com.wanted.recruitment.dto;

public record FindRecruitmentResponse(Long recruitmentId, String recruitmentName, String companyName, String nation,
									  String area,
									  String position, int compensation, String skill) {
}
