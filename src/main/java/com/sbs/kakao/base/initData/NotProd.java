package com.sbs.kakao.base.initData;

import com.sbs.kakao.domain.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration // spring에서 해당 클래스가 구성 클래스임을 나타냄
@Profile("!prod") // 특정 프로파일이 활성화되지 않을 때만 해당 구성이 활성화되도록 지정
public class NotProd {
    @Bean // 해당 메서드가 빈으로 등록될 수 있음을 나타냄
    public ApplicationRunner init(MemberService memnerservice){
        return args -> {
            memnerservice.join("admin", "1234", "admin");

            IntStream.range(1,3).forEach(i -> {
                memnerservice.join("templates/user" +i, "1234", "nickname" + i);
            }); // 1부터 2까지의 숫자 범위에서 반복하면서 memeberservice의 join 메서드를 호출
        };
    }
}
// ApplicationRunner : spring boot 애플리케이션이 실행될 때 특정 작업을 수행할 수있도록 하는 인터페이스
