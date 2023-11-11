package org.sopt.kotlinsemina.domain.category.model

import java.lang.IllegalArgumentException

object CategoryMapper {
    private val categoryMap = mapOf(
        1.toShort() to "Java",
        2.toShort() to "Spring",
        3.toShort() to "React",
        4.toShort() to "Python"
    )

    fun getCategoryNameById(categoryId: Short): String {
        return categoryMap[categoryId] ?: throw IllegalArgumentException("해당 카테고리명은 존재하지 않습니다")
    }
}