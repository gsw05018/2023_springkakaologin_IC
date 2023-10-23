package com.sbs.kakao.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // JPA가 이 클래스를 데이터베이스의 테이블가 매핑할 수있도록 짖정
@EqualsAndHashCode // Equals와 HashCode 메소드 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자덩생성
@AllArgsConstructor // 매개변수가 있는 생성자 자동생성
@Builder // 빌더 패턴을 사용하여 객체 생성 가능 지정
@Getter
public class Member {

    @EqualsAndHashCode.Include // Equals, HashCode에 포함될 필드 지정
    @Id // 엔티티의 식별자키임을 지정
    @GeneratedValue(strategy = IDENTITY) // 식별자 자동 생성
    private Long id;
    private String username;
    private String password;
    private String nickname;

    public List<? extends GrantedAuthority> getGrantedAuthorities(){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        if(isAdmin()){
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        }
        return grantedAuthorities;
    }

    public boolean isAdmin(){
        return "admin".equals(username);
    }


}
// RequiredArgsConstructor어노테이션은 JPA 클래스에서 권장하지 않음
// 기본생성자가 생성되지 않을 수 있을 가능성 있음
// JPA가 엔티티를 생성하고 관리하는데 문제 발생
// 엔티티클래스에서는 AllArgsConstructor, NoArgsConstructor 사용하여 생성자 관리
