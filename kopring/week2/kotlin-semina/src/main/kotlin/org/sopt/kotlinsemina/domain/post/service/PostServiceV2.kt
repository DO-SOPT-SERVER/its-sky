package org.sopt.kotlinsemina.domain.post.service

import org.sopt.kotlinsemina.domain.category.model.CategoryId
import org.sopt.kotlinsemina.domain.member.exception.MemberNotFoundException
import org.sopt.kotlinsemina.domain.member.repository.MemberRepository
import org.sopt.kotlinsemina.domain.post.dto.PostCreateRequest
import org.sopt.kotlinsemina.domain.post.exception.PostNotFoundException
import org.sopt.kotlinsemina.domain.post.model.Post
import org.sopt.kotlinsemina.domain.post.repository.PostRepository
import org.sopt.kotlinsemina.global.common.service.S3Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(readOnly = true)
class PostServiceV2(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository,
    private val s3Service: S3Service
) {

    @Transactional
    fun createV2(request: PostCreateRequest, image: MultipartFile, memberId: Long): String {
        val imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image)
        val member = memberRepository.findByIdOrNull(memberId) ?: throw MemberNotFoundException()
        val categoryId = CategoryId(request.categoryId)
        val post = postRepository.save(
            Post(
                request.title,
                request.content,
                member,
                categoryId,
                imageUrl
            )
        )
        return post.id.toString()
    }

    @Transactional
    fun deleteByIdV2(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException()
        if (post.imageUrl != null) s3Service.deleteImage(post.imageUrl!!)
        postRepository.delete(post)
    }

    companion object {
        private const val POST_IMAGE_FOLDER_NAME = "posts/"
    }
}