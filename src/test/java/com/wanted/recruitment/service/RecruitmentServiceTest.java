package com.wanted.recruitment.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wanted.company.domain.Company;
import com.wanted.company.repository.CompanyRepository;
import com.wanted.recruitment.domain.Recruitment;
import com.wanted.recruitment.dto.CreateRecruitmentRequest;
import com.wanted.recruitment.dto.FindRecruitmentResponse;
import com.wanted.recruitment.repository.RecruitmentRepository;

@SpringBootTest
class RecruitmentServiceTest {

	@InjectMocks
	private RecruitmentService recruitmentService;

	@Mock
	private RecruitmentRepository recruitmentRepository;

	@Mock
	private CompanyRepository companyRepository;

	private Company company;

	private Recruitment recruitment, recruitment2;

	@BeforeEach
	public void init() {
		company = Company.builder()
			.id(1L)
			.name("원티드랩")
			.nation("한국")
			.area("서울")
			.build();

		recruitment = Recruitment.builder()
			.id(1L)
			.name("원티드랩 개발자 모집")
			.position("백엔드 개발자")
			.compensation(100000)
			.content("채용 합니다")
			.skill("Java")
			.company(company)
			.build();
		recruitment2 = Recruitment.builder()
			.id(2L)
			.name("원티드랩 개발자 모집2")
			.position("백엔드 개발자2")
			.compensation(200000)
			.content("채용 합니다2")
			.skill("Python")
			.company(company)
			.build();
	}

	@Test
	@DisplayName("채용공고 등록")
	void create() {
		// given
		CreateRecruitmentRequest dto = CreateRecruitmentRequest.builder()
			.companyId(company.getId())
			.position("백엔드 주니어 개발자")
			.compensation(100000)
			.content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
			.skill("Python")
			.build();

		// when
		doReturn(recruitment).when(recruitmentRepository).save(any());
		doReturn(Optional.of(company)).when(companyRepository).findById(company.getId());
		recruitmentService.create(dto);

		// then
		verify(recruitmentRepository, times(1)).save(any(Recruitment.class));
	}

	@Test
	@DisplayName("채용공고 전체 조회")
	void findAll() {
		// given

		// when
		doReturn(List.of(recruitment, recruitment2)).when(recruitmentRepository).findAll();
		List<FindRecruitmentResponse> findRecruitmentResponses = recruitmentService.findAll();

		// then
		assertThat(findRecruitmentResponses).hasSize(2);
		assertThat(findRecruitmentResponses.get(0).skill()).isEqualTo("Java");
		assertThat(findRecruitmentResponses.get(1).compensation()).isEqualTo(200000);
	}

	@Test
	@DisplayName("채용공고 상세 조회 실패")
	void findById_fail() {
		// given

		// when

		// then
		assertThatThrownBy(() -> recruitmentService.findById(2L)).isInstanceOf(NoSuchElementException.class)
			.hasMessage("채용공고 ID 값에 해당하는 채용공고를 찾을 수 없습니다.");
	}

	@Test
	@DisplayName("채용공고 키워드 조회 성공")
	void findBySearchParam_success() {
		// given
		String search = "Python";

		// when
		doReturn(List.of(recruitment2)).when(recruitmentRepository).findBySearchParam(search);
		List<FindRecruitmentResponse> findRecruitmentResponses = recruitmentService.findBySearchParam(search);

		// then
		assertThat(findRecruitmentResponses).hasSize(1);
		assertThat(findRecruitmentResponses.get(0).skill()).isNotEqualTo("Java");
		assertThat(findRecruitmentResponses.get(0).skill()).isEqualTo("Python");
	}

	@Test
	@DisplayName("채용공고 키워드 조회 실패")
	void findBySearchParam_fail() {
		// given
		String search = "C++";

		// when
		doReturn(null).when(recruitmentRepository).findBySearchParam(search);

		// then
		assertThatThrownBy(() -> recruitmentService.findBySearchParam(search)).isInstanceOf(
			NoSuchElementException.class).hasMessage("조회된 결과가 없습니다.");
	}
}
