package com.example.FastcampusSpringBasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    @Autowired
    MemberService memberService;
    //3. 스프링 컨테이너에 객체 제어권을 역전하여 의존성 주입을 통해 필요할 때 쓰면 알아서 생성 및 다룰 수 있게 해준다.

    public void demo() {

//        MemberService memberService = new MemberService();
            //1. 객체 직접 생성

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
            //2. 메인클래스에서 객체를 생성하던 주권을 뺏어서 AppConfig 에서 생성하고, 필요할 때 호출하여 사용

        // 스프링 컨테이너의 실체
//        ApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//        MemberService memberService =
//                applicationContext.getBean("memberService", MemberService.class);

        memberService.welcome("kim songa");
    }
}