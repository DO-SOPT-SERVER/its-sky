package org.sopt.Seminar.domain.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.member.dto.MemberCreateRequest;
import org.sopt.Seminar.domain.member.dto.MemberGetResponse;
import org.sopt.Seminar.domain.member.dto.MemberProfileUpdateRequest;
import org.sopt.Seminar.domain.member.exception.UserNotFoundException;
import org.sopt.Seminar.domain.member.model.Member;
import org.sopt.Seminar.domain.member.model.SOPT;
import org.sopt.Seminar.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberGetResponse getMemberByIdV1(Long id) {
		return MemberGetResponse.of(memberRepository.findById(id).get());
		// Optional 객체 내부가 비었는데 get()을 하게 된다면 Exception 잠재적 발생 가능.
	}

	public MemberGetResponse getMemberByIdV2(Long id) {
		return MemberGetResponse.of(memberRepository.findById(id).orElseThrow(UserNotFoundException::new));
	}

	public MemberGetResponse getMemberByIdV3(Long id) {
		return MemberGetResponse.of(memberRepository.findByIdOrThrow(id));
	}

	public MemberGetResponse getMemberByIdV4(Long id) {
		return MemberGetResponse.of(findByIdOrThrow(id));
		// v3와 v4는 메소드 구현부만 Repository, Service Layer인지만 다름.
		// java 8 이후에 인터페이스에도 default method 선언이 가능함.
	}

	public Member findByIdOrThrow(Long id) {
		return memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	public List<MemberGetResponse> getMembers() {
		List<Member> entityList = memberRepository.findAll();

		return entityList.stream().map(MemberGetResponse::of).toList();
	}

	@Transactional
	public String create(MemberCreateRequest request) {
		Member member = Member.builder()
				.name(request.name())
				.nickname(request.nickname())
				.age(request.age())
				.sopt(request.sopt())
				.build();

		return memberRepository.save(member).getId().toString();
	}

	@Transactional
	public void updateSOPT(Long id, MemberProfileUpdateRequest request) {
		Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
		member.updateSOPT(new SOPT(request.getGeneration(), request.getPart()));
	}

	@Transactional
	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
	}
}
