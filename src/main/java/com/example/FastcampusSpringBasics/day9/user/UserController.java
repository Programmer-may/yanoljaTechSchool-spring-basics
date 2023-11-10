package com.example.FastcampusSpringBasics.day9.user;

import com.example.FastcampusSpringBasics.day9.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    UserService userService;
    JwtTokenProvider jwtTokenProvider;

    UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/join")
    public User join() {
        User user = new User();
        user.setEmail("tester@mail.com");
        user.setPassword("123");
        user.setRole("ROLE_USER");

        return userService.save(user);
    }

    @PostMapping("/login")
    public String login(
        @RequestBody Map<String, String> loginUser) {
        // username, password
        User user = userService.login(loginUser);

        if (user != null) {
            return jwtTokenProvider
                .createToken(user.getUsername(),
                    user.getAuthorities());
        } else {
            return "로그인 실패";
        }

    }
}
