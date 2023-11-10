package com.example.FastcampusSpringBasics.day9.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // WebSecurityConfig -> UserService로 이사
        // DB에 너 있는거 확인했으니까, 들여보내줄게

        return userRepository.findByEmail(username); // userRepository에 회원가입(저장)되어있는 객체를 꺼내주면 됩니다.
    }

    public User login(Map<String, String> loginUser) {

        return userRepository.findByEmail(loginUser.get("username"));
    }
}

