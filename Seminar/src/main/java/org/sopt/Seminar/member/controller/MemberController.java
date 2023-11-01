package org.sopt.Seminar.member.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.member.dto.MemberCreateRequest;
import org.sopt.Seminar.member.dto.MemberGetResponse;
import org.sopt.Seminar.member.dto.MemberProfileUpdateRequest;
import org.sopt.Seminar.member.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;
	private final String MEMBER_API_ENDPOINT = "/api/member/";

	@GetMapping("/{memberId}/v1")
	public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
		return ResponseEntity.ok().body(memberService.getMemberByIdV2(memberId));
	}

	@GetMapping(value = "{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
		return ResponseEntity.ok().body(memberService.getMemberByIdV2(memberId));
	}

	@GetMapping
	public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
		return ResponseEntity.ok().body(memberService.getMembers());
	}

	@PostMapping
	public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
		URI location = URI.create(MEMBER_API_ENDPOINT + memberService.create(request));
		return ResponseEntity.created(location).build();
	}

	@PatchMapping("/{memberId}")
	public ResponseEntity<Void> updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequest request) {
		memberService.updateSOPT(memberId, request);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
		memberService.deleteMember(memberId);
		return ResponseEntity.noContent().build();
	}
}
