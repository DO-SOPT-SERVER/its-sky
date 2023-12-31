package org.sopt.kotlinsemina.domain.member.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.GenerationType.*
import jakarta.persistence.Id
import org.sopt.kotlinsemina.global.common.model.BaseTimeEntity

@Entity
class Member(
    @Id @GeneratedValue(strategy = IDENTITY)
    var id: Long ?= null,

    var name: String,
    var nickname: String,
    var age: Int,
    var sopt: SOPT,
): BaseTimeEntity() {
    fun updateSOPT(sopt: SOPT) {
        this.sopt = sopt
    }
}