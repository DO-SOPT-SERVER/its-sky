package org.sopt.Seminar.domain.member.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.Seminar.global.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	private String nickname;

	private int age;

	@Embedded
	private SOPT sopt;

	@Builder
	public Member(String name, String nickname, int age, SOPT sopt) {
		this.name = name;
		this.nickname = nickname;
		this.age = age;
		this.sopt = sopt;
	}

	public void updateSOPT(SOPT sopt) {
		this.sopt = sopt;
	}
}
