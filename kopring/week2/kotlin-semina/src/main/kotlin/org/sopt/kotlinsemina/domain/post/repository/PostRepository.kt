package org.sopt.kotlinsemina.domain.post.repository

import org.sopt.kotlinsemina.domain.member.model.Member
import org.sopt.kotlinsemina.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByMember(member: Member) : List<Post>
}