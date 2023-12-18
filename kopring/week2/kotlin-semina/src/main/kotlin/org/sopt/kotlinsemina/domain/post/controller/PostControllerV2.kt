package org.sopt.kotlinsemina.domain.post.controller

import org.sopt.kotlinsemina.domain.post.dto.PostCreateRequest
import org.sopt.kotlinsemina.domain.post.service.PostServiceV2
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@RestController
@RequestMapping("/api/v2/posts")
class PostControllerV2(
    private val postService: PostServiceV2
) {

    @PostMapping
    fun createPostV2(@RequestHeader(CUSTOM_AUTH_ID) memberId: Long,
                     @RequestPart image: MultipartFile,
                     request: PostCreateRequest): ResponseEntity<Void> {
        val location = URI.create(POST_API_ENDPOINT + postService.createV2(request, image, memberId))
        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deleteByIdV2(postId)
        return ResponseEntity.noContent().build()
    }

    companion object {
        private const val CUSTOM_AUTH_ID = "X-Auth-Id"
        private const val POST_API_ENDPOINT = "/api/posts/v2"
    }
}