package org.sopt.kotlinsemina.domain.category.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Category(
    @Id @Column(name = "category_id")
    val categoryId: CategoryId,
    val content: String
) {
}