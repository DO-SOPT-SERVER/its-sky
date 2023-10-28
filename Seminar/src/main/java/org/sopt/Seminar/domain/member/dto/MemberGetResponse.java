package org.sopt.Seminar.domain.member.dto;

import org.sopt.Seminar.domain.member.model.Member;
import org.sopt.Seminar.domain.member.model.SOPT;

public record MemberGetResponse(
		String name,
		String nickname,
		int age,
		SOPT soptInfo
) {
	public static MemberGetResponse of(Member member) {
		return new MemberGetResponse(
				member.getName(),
				member.getNickname(),
				member.getAge(),
				member.getSopt()
		);
	}
}
