package org.sopt.kotlinsemina.domain.post.dto

data class PostCreateRequest(
    val title: String,
    val content: String,
    val categoryId: Short
)
