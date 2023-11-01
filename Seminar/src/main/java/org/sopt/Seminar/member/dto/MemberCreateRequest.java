package org.sopt.Seminar.member.dto;

import lombok.Data;
import org.sopt.Seminar.member.domain.SOPT;

public record MemberCreateRequest(
		String name,
		String nickname,
		int age,
		SOPT sopt
) {
}
