package com.wanted.recruitment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.recruitment.service.RecruitmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
public class RecruitmentController {

	private final RecruitmentService recruitmentService;

	@GetMapping
	public void getRecruitments() {

	}
}
