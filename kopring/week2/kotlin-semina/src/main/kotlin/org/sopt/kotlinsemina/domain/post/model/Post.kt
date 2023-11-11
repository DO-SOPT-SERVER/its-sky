package org.sopt.kotlinsemina.domain.post.model

import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import jakarta.persistence.GenerationType.*
import org.sopt.kotlinsemina.domain.category.model.CategoryId
import org.sopt.kotlinsemina.domain.member.model.Member
import org.sopt.kotlinsemina.global.common.model.BaseTimeEntity

@Entity
class Post(
    title: String,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @Column(name = "category_id")
    val categoryId: CategoryId,

    content: String
    ): BaseTimeEntity() {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    val id: Long = 0

    var title: String = title
        private set

    @Column(columnDefinition = "TEXT")
    var content: String = content
        private set

    fun updateContent(content: String) {
        this.content = content
    }
}