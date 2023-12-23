package org.sopt.kotlinsemina.domain.post.controller

import jakarta.validation.Valid
import org.sopt.kotlinsemina.domain.post.dto.PostCreateRequest
import org.sopt.kotlinsemina.domain.post.dto.PostGetResponse
import org.sopt.kotlinsemina.domain.post.dto.PostUpdateRequest
import org.sopt.kotlinsemina.domain.post.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.security.Principal

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postService: PostService
) {
    @PostMapping
    fun createPost(@Valid @RequestBody request: PostCreateRequest,
                   principal: Principal) : ResponseEntity<Void> {
        val memberId = principal.name.toLong()
        val location = URI.create(POST_API_ENDPOINT + postService.create(request, memberId))

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): ResponseEntity<PostGetResponse> {
        return ResponseEntity.ok().body(postService.getById(postId))
    }

    @GetMapping
    fun getPosts(@RequestHeader(CUSTOM_AUTH_ID) memberId: Long): ResponseEntity<List<PostGetResponse>> {
        return ResponseEntity.ok().body(postService.getPosts(memberId))
    }

    @PatchMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long,
                   @RequestBody request: PostUpdateRequest): ResponseEntity<Void> {
        postService.editContent(postId, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deleteById(postId)
        return ResponseEntity.noContent().build()
    }

    companion object {
        private const val CUSTOM_AUTH_ID: String = "X-Auth-Id"
        private const val POST_API_ENDPOINT: String = "/api/post/"
    }
}