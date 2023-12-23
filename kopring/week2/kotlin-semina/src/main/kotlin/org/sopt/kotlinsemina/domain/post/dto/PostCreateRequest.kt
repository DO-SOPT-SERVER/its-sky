package org.sopt.kotlinsemina.domain.post.dto

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.lang.IllegalArgumentException

data class PostCreateRequest(
    @Size(min = 10, max = 10, message = "제목은 1글자 이상 10글자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\\\s]*\$", message = "제목은 한글, 영문, 숫자만 입력 가능합니다.")
    val title: String,
    val content: String,
    val categoryId: Short
) {
    @AssertTrue(message = "내용은 10자 이상으로 작성해주세요.")
    fun isValidContent(): Boolean {
        try {
            return this.content.length >= 10
        } catch (e: RuntimeException) {
            return false
        }
    }

    fun validateTitle() {
        if (this.title.isBlank()) {
            throw IllegalArgumentException("제목은 공백으로 이뤄질 수 없습니다.")
        }
    }
}
