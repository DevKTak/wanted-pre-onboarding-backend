package com.wanted.user.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.recruitment.dto.ApplyRecruitmentRequest;
import com.wanted.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@PutMapping
	public void applyRecruitment(@RequestBody ApplyRecruitmentRequest applyRecruitmentRequest) {
		userService.applyRecruitment(applyRecruitmentRequest);
	}
}
