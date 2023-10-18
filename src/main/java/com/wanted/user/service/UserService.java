package com.wanted.user.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.recruitment.domain.Recruitment;
import com.wanted.recruitment.dto.ApplyRecruitmentRequest;
import com.wanted.recruitment.repository.RecruitmentRepository;
import com.wanted.user.domain.User;
import com.wanted.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;

	private final RecruitmentRepository recruitmentRepository;

	@Transactional
	public void applyRecruitment(ApplyRecruitmentRequest applyRecruitmentRequest) {
		User user = userRepository.findById(applyRecruitmentRequest.userId())
			.orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));

		if (user.getRecruitment() != null) {
			throw new IllegalStateException("사용자는 1회만 지원 가능합니다.");
		}
		Recruitment recruitment = recruitmentRepository.findById(applyRecruitmentRequest.recruitmentId())
			.orElseThrow(() -> new NoSuchElementException("채용공고 ID 값에 해당하는 채용공고를 찾을 수 없습니다."));

		user.applyRecruitment(recruitment);
	}
}
