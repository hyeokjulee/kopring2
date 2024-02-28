package com.example.demo.member.service

import com.example.demo.member.dto.MemberDtoRequest
import com.example.demo.member.entity.Member
import com.example.demo.member.repositoty.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest): Boolean {
        if (idExist(memberDtoRequest)) return false // ID 중복 검사
        memberRepository.save(dtoToEntity(memberDtoRequest))
        return true
    }

    private fun idExist(memberDtoRequest: MemberDtoRequest): Boolean {
        return memberRepository.findByLoginId(memberDtoRequest.loginId) != null
    }

    private fun dtoToEntity(memberDtoRequest: MemberDtoRequest): Member {
        return Member(
            null,
            memberDtoRequest.loginId,
            memberDtoRequest.password,
            memberDtoRequest.name,
            memberDtoRequest.birthDate,
            memberDtoRequest.gender,
            memberDtoRequest.email
        )
    }
}