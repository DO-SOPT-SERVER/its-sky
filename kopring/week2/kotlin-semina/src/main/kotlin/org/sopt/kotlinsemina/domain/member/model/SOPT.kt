package org.sopt.kotlinsemina.domain.member.model

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class SOPT(
    val generation: Int,

    @Enumerated(EnumType.STRING)
    val part: Part,
) {
}