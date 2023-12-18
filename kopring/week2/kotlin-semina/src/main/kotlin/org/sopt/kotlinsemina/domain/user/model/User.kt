package org.sopt.kotlinsemina.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.GenerationType.*
import jakarta.persistence.Id

class User(
    nickname: String,
    password: String
) {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0

    var nickname: String = nickname
        private set

    var password: String = password
        private set
}