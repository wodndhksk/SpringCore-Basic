package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", // 이렇게 스캔할 위치를 지정할 수 있다 (Default 위치는 현 클래스 패키지)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository") // MemoryMemberRepository 와 이름이 같음(고의충돌 테스트)
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
