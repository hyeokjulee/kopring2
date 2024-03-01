package com.example.demo.member.service

import com.example.demo.common.exception.InvalidInputException
import com.example.demo.common.status.ROLE
import com.example.demo.member.dto.MemberDtoRequest
import com.example.demo.member.entity.Member
import com.example.demo.member.entity.MemberRole
import com.example.demo.member.repositoty.MemberRepository
import com.example.demo.member.repositoty.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository
) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest) {
        if (memberRepository.findByLoginId(memberDtoRequest.loginId) != null) { // ID 중복 검사
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        val member = memberDtoRequest.toEntity()
        memberRepository.save(member)

        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)
    }
}