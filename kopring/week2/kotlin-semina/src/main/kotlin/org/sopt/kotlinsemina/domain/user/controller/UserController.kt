package org.sopt.kotlinsemina.domain.user.controller

import org.sopt.kotlinsemina.domain.user.dto.request.UserRequest
import org.sopt.kotlinsemina.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/users/")
class UserController(
    private val userService: UserService
) {

    @PostMapping("sign-up")
    fun signUp(@RequestBody request: UserRequest): ResponseEntity<Void> {
        val location = URI.create(userService.create(request))
        return ResponseEntity.created(location).build()
    }

    @PostMapping("sign-in")
    fun signIn(@RequestBody request: UserRequest): ResponseEntity<Void> {
        userService.signIn(request)
        return ResponseEntity.noContent().build()
    }
}