package com.sbs.kakao.domain.member.service;

import com.sbs.kakao.domain.member.entity.Member;
import com.sbs.kakao.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 롬북을 사용하여 fianl 생성자 자동 생성
public class MemberService {
    private final MemberRepository memberRepository; // 의존성 주입
    private final PasswordEncoder passwordEncoder; //의존성 주입

    public Member join(String username, String password, String nickname){
        // 주어진 정보를 사용하여 member 객체 생성
        Member member = Member
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // 비밀번호 인코딩
                .nickname(nickname)
                .build();
    
        // 생성된 member 객체를 저장하고 반환
        return memberRepository.save(member);

    }
}


