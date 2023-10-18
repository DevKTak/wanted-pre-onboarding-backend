package com.wanted.recruitment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.recruitment.dto.CreateRecruitmentRequest;
import com.wanted.recruitment.dto.FindRecruitmentDetailResponse;
import com.wanted.recruitment.dto.FindRecruitmentResponse;
import com.wanted.recruitment.dto.UpdateRecruitmentRequest;
import com.wanted.recruitment.service.RecruitmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
public class RecruitmentController {

	private final RecruitmentService recruitmentService;

	@PostMapping
	public void create(@Valid @RequestBody CreateRecruitmentRequest createRecruitmentRequest) {
		recruitmentService.create(createRecruitmentRequest);
	}

	@PutMapping("/{recruitmentId}")
	public void update(@Valid @RequestBody UpdateRecruitmentRequest updateRecruitmentRequest,
		@PathVariable Long recruitmentId) {
		recruitmentService.update(updateRecruitmentRequest, recruitmentId);
	}

	@DeleteMapping
	public void deleteAll() {
		recruitmentService.deleteAll();
	}

	@GetMapping
	public List<FindRecruitmentResponse> findAll() {
		return recruitmentService.findAll();
	}

	@GetMapping("/{recruitmentId}")
	public FindRecruitmentDetailResponse findById(@PathVariable Long recruitmentId) {
		return recruitmentService.findById(recruitmentId);
	}

	@GetMapping("/search")
	public List<FindRecruitmentResponse> findBySearchParam(@RequestParam String search) {
		return recruitmentService.findBySearchParam(search);
	}
}
