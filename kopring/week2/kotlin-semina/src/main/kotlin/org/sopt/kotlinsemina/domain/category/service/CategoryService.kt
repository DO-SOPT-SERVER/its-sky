package org.sopt.kotlinsemina.domain.category.service

import org.sopt.kotlinsemina.domain.category.model.Category
import org.sopt.kotlinsemina.domain.category.model.CategoryId
import org.sopt.kotlinsemina.domain.category.repository.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
@Transactional(readOnly = true)
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun getByCategoryId(categoryId: CategoryId): Category {
        return categoryRepository.findByIdOrNull(categoryId) ?: throw IllegalArgumentException("해당 카테고리가 존재하지 않습니다")
    }
}