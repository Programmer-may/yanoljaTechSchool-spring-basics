package com.example.FastcampusSpringBasics;


import com.example.FastcampusSpringBasics.day1.MemberService;
import com.example.FastcampusSpringBasics.day3.DBRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이 클래스 안에 객체 생성하는 메소드 보이지?
// 그 메소드가 반환하는 객체를 "스프링 빈"으로 등록 해줘 = 스프링 컨테이너에 등록 해줘
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService();
    }

    @Bean
    public DBRepository dbRepository() {
        return new DBRepository();
    }
}
