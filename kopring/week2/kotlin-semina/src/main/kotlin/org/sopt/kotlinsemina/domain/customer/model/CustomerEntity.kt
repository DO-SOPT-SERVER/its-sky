package org.sopt.kotlinsemina.domain.customer.model

import jakarta.persistence.*
import org.springframework.util.Assert

@Entity
@Table(name = "customer")
class CustomerEntity(
    name: String,
    age: Int,
    nickname: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    val id: Long = 0

    @Column(nullable = false)
    var name = name
    private set

    @Column(nullable = false)
    var age = age
    private set

    @Column(nullable = false)
    var nickname = nickname
    private set

    init {
        validateName(name)
        validateAge(age)
        validateNickname(nickname)

        this.name = name
        this.age = age
        this.nickname = nickname
    }

    private fun validateName(name: String) {
        Assert.notNull(nickname, "닉네임은 빈 값이 될 수 없습니다.")
    }

    private fun validateAge(age: Int) {
        Assert.isTrue(age > 0 && age < 200, "나이는 1살부터 200살 아래여야 합니다.")
    }

    private fun validateNickname(nickname: String) {
        Assert.notNull(nickname, "닉네임은 빈 값이 될 수 없습니다.")
    }
}