package org.sopt.kotlinsemina.domain.post.dto

import org.sopt.kotlinsemina.domain.category.model.Category
import org.sopt.kotlinsemina.domain.post.model.Post

data class PostGetResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
) {
    companion object {
        fun of(post: Post, category: Category): PostGetResponse {
            return PostGetResponse(
                post.id,
                post.title,
                post.content,
                category.content)
        }
    }
}
