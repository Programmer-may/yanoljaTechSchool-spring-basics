package com.example.FastcampusSpringBasics.day9.user;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<String, User> db = new HashMap<>();
    private int idx = 0;

    public User save(User user) {
        user.setId(idx++);
        db.put(user.getUsername(), user);
        return user;
    }

    public User findByEmail(String username) {
        return db.get(username);
    }
}
