package org.sopt.kotlinsemina.domain.member.dto

import org.sopt.kotlinsemina.domain.member.model.Member
import org.sopt.kotlinsemina.domain.member.model.SOPT

data class MemberGetResponse(
    val name: String,
    val nickname: String,
    val age: Int,
    val soptInfo: SOPT, // 프로퍼티 private으로 설정 시 jackson 라이브러리가 json에 값 주입을 못해줌.
) {
    companion object {
        fun of(member: Member): MemberGetResponse {
            return MemberGetResponse(
                name = member.name,
                nickname = member.nickname,
                age = member.age,
                soptInfo = member.sopt
            )
        }
    }
}
