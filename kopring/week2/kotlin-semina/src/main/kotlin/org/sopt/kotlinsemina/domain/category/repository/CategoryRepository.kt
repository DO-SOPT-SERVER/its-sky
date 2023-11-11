package org.sopt.kotlinsemina.domain.category.repository

import org.sopt.kotlinsemina.domain.category.model.Category
import org.sopt.kotlinsemina.domain.category.model.CategoryId
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, CategoryId> {
}