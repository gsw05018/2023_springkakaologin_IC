package com.sbs.kakao.base.security;

import com.sbs.kakao.domain.member.entity.Member;
import com.sbs.kakao.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기전용 트랜잭션으로 설정하는 어노테이션
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository; // memberRepository에 의존성 주입

    @Override // UserDetailsService의 메서드 재정의
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{ // 주어진 username으로 유저 정보를 불러오는 메서드
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new // 주어진  username으로 member찾음
                    UsernameNotFoundException("username(%s) not found".formatted(username))); // 찾지 못한 경우 예외던짐
        return new User(member.getUsername(), member.getPassword(), member.getGrantedAuthorities()); // 찾은 Member를 기반으로 UserDetails를 생성하여 반환 
    }
}
