package org.sopt.Seminar.member.dto;

import org.sopt.Seminar.member.domain.Member;
import org.sopt.Seminar.member.domain.SOPT;

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
