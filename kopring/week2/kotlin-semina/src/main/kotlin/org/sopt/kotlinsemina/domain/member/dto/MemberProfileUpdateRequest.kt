package org.sopt.kotlinsemina.domain.member.dto

import org.sopt.kotlinsemina.domain.member.model.Part

data class MemberProfileUpdateRequest(
    val generation: Int,
    val part: Part,
)
