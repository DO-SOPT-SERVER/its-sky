package org.sopt.kotlinsemina.domain.user.service

import org.sopt.kotlinsemina.domain.member.exception.MemberNotFoundException
import org.sopt.kotlinsemina.domain.user.dto.request.UserRequest
import org.sopt.kotlinsemina.domain.user.model.User
import org.sopt.kotlinsemina.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun create(request: UserRequest): String {
        val user = userRepository.save(
            User(
                nickname = request.nickname,
                password = request.password
            )
        )

        return user.id.toString()
    }

    fun signIn(request: UserRequest) {
        val user = userRepository.findByNickname(request.nickname)
            ?: throw RuntimeException("해당하는 회원이 없습니다.")
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }
    }
}