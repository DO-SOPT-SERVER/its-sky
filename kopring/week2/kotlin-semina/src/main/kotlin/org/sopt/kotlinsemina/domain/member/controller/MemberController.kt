package org.sopt.kotlinsemina.domain.member.controller

import lombok.RequiredArgsConstructor
import org.apache.coyote.Response
import org.sopt.kotlinsemina.domain.member.dto.MemberCreateRequest
import org.sopt.kotlinsemina.domain.member.dto.MemberGetResponse
import org.sopt.kotlinsemina.domain.member.dto.MemberProfileUpdateRequest
import org.sopt.kotlinsemina.domain.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService,
    ) {
    private val MEMBER_API_ENDPOINT: String = "/api/member/"

    @PostMapping
    fun createMember(@RequestBody request: MemberCreateRequest): ResponseEntity<Void> {
        val location = URI.create(MEMBER_API_ENDPOINT + memberService.create(request))
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/v1/{memberId}")
    fun getMemberProfile(@PathVariable memberId: Long): ResponseEntity<MemberGetResponse> {
        return ResponseEntity.ok().body(memberService.getMemberById(memberId));
    }

    @GetMapping
    fun getMembersProfile(): ResponseEntity<List<MemberGetResponse>> {
        return ResponseEntity.ok().body(memberService.getMembers())
    }

    @PatchMapping("/{memberId}")
    fun updateMemberSoptInfo(@PathVariable memberId: Long, @RequestBody request: MemberProfileUpdateRequest): ResponseEntity<Void> {
        memberService.updateSOPT(memberId, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable memberId: Long): ResponseEntity<Void> {
        memberService.deleteMember(memberId)
        return ResponseEntity.noContent().build()
    }
}