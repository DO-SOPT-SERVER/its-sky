package org.sopt.kotlinsemina.domain.member.service

import jakarta.persistence.EntityNotFoundException
import org.sopt.kotlinsemina.domain.member.dto.MemberCreateRequest
import org.sopt.kotlinsemina.domain.member.dto.MemberGetResponse
import org.sopt.kotlinsemina.domain.member.dto.MemberProfileUpdateRequest
import org.sopt.kotlinsemina.domain.member.model.Member
import org.sopt.kotlinsemina.domain.member.model.SOPT
import org.sopt.kotlinsemina.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun create(request: MemberCreateRequest): String {
        val member =
            Member(name = request.name, nickname = request.nickname, age = request.age, sopt = request.soptInfo)

        return memberRepository.save(member).id.toString();
    }

    fun getMemberById(memberId: Long): MemberGetResponse {
        val member = memberRepository.findById(memberId).orElseThrow{
            throw EntityNotFoundException("존재하지 않는 회원입니다.")
        } // java에서는 () -> throw new ~~ 이렇게 하는데 문법적으로 약간 다름.

        return MemberGetResponse.of(member)
    }

    fun getMembers(): List<MemberGetResponse> {
        val memberList = memberRepository.findAll()
        return memberList.map { MemberGetResponse.of(it) }.toList()
        // it은 코틀린에서 사용되는 람다 키워드라고 생각하자!
        // 또 java에서는 memberList.stream().map(~) 이런식으로 했던거 같은데 굉장히 간결..?하다
    }

    @Transactional
    fun updateSOPT(memberId: Long, request: MemberProfileUpdateRequest) {
        val member = memberRepository.findById(memberId).orElseThrow {
            throw EntityNotFoundException("존재하지 않는 회원입니다.")
        }

        member.updateSOPT(SOPT(request.generation, request.part)) // Transactional 안붙이고 왜 더티체킹 안되는지만 확인하다가.. 시간버림
    }

    @Transactional
    fun deleteMember(memberId: Long) {
        memberRepository.deleteById(memberId);
    }
}