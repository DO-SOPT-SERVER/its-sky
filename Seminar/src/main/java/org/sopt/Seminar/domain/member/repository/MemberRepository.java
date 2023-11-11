package org.sopt.Seminar.domain.member.repository;

import org.sopt.Seminar.domain.member.exception.MemberNotFoundException;
import org.sopt.Seminar.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	default Member findByIdOrThrow(Long id) {
		return findById(id)
				.orElseThrow(MemberNotFoundException::new);
	}
}
