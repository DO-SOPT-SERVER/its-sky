package org.sopt.kotlinsemina.domain.post.service

import org.sopt.kotlinsemina.domain.category.model.Category
import org.sopt.kotlinsemina.domain.category.model.CategoryId
import org.sopt.kotlinsemina.domain.category.model.CategoryMapper
import org.sopt.kotlinsemina.domain.category.repository.CategoryRepository
import org.sopt.kotlinsemina.domain.category.service.CategoryService
import org.sopt.kotlinsemina.domain.member.repository.MemberRepository
import org.sopt.kotlinsemina.domain.post.dto.PostCreateRequest
import org.sopt.kotlinsemina.domain.post.dto.PostGetResponse
import org.sopt.kotlinsemina.domain.post.dto.PostUpdateRequest
import org.sopt.kotlinsemina.domain.post.model.Post
import org.sopt.kotlinsemina.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
    private val categoryRepository: CategoryRepository,
    private val categoryService: CategoryService
) {
    @Transactional
    fun create(request: PostCreateRequest, memberId: Long): String {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw IllegalArgumentException("회원이 존재하지 않습니다")
        val categoryId = CategoryId(request.categoryId)
        val post = postRepository.save(
            Post(
                title = request.title,
                content = request.content,
                member = member,
                categoryId = categoryId
            )
        )
        categoryRepository.save(
            Category(categoryId, CategoryMapper.getCategoryNameById(request.categoryId))
        )

        return post.id.toString()
    }

    fun getById(postId: Long): PostGetResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("게시물이 존재하지 않습니다")

        return PostGetResponse.of(post, getCategoryByPost(post))
    }

    fun getPosts(memberId: Long): List<PostGetResponse> {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw IllegalArgumentException("회원이 존재하지 않습니다")
        val posts = postRepository.findAllByMember(member)

        return posts.map { PostGetResponse.of(it, getCategoryByPost(it)) }.toList()
    }

    @Transactional
    fun editContent(postId: Long, request: PostUpdateRequest) {
        val post = postRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("게시물이 존재하지 않습니다")
        post.updateContent(request.content)
    }

    @Transactional
    fun deleteById(postId: Long) {
        postRepository.deleteById(postId)
    }

    private fun getCategoryByPost(post: Post): Category {
        return categoryService.getByCategoryId(post.categoryId)
    }
}