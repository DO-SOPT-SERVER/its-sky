package org.sopt.Seminar.member.repository;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.Seminar.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	default Member findByIdOrThrow(Long id) {
		return findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당하는 회원이 없습니다"));
	}
}
