package com.example.demo.member.service

import com.example.demo.common.exception.InvalidInputException
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
    fun signUp(memberDtoRequest: MemberDtoRequest) {
        if (memberRepository.findByLoginId(memberDtoRequest.loginId) != null) { // ID 중복 검사
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }
        memberRepository.save(memberDtoRequest.toEntity())
    }
}