package org.sopt.kotlinsemina.domain.member.repository

import org.sopt.kotlinsemina.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
}