package org.sopt.Seminar.domain.member.dto;

import org.sopt.Seminar.domain.member.model.SOPT;

public record MemberCreateRequest(
		String name,
		String nickname,
		int age,
		SOPT sopt
) {
}
