package org.sopt.kotlinsemina.domain.user.repository

import org.sopt.kotlinsemina.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByNickname(nickname: String): User?
}