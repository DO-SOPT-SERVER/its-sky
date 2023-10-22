package org.sopt.kotlinsemina.domain.member.dto

import org.sopt.kotlinsemina.domain.member.model.SOPT

data class MemberCreateRequest(
    val name: String,
    val nickname: String,
    val age: Int,
    val soptInfo: SOPT,
)
