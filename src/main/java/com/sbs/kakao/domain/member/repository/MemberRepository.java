package com.sbs.kakao.domain.member.repository;


import com.sbs.kakao.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
