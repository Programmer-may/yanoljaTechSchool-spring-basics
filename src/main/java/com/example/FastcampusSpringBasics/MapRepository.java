package com.example.FastcampusSpringBasics;

import java.util.HashMap;
import java.util.Map;

/**
 * DB가 없을 때
 * RDBMS -> table -> key-value Map
 */
public class MapRepository implements Repository{

    Map<Integer, String> db = new HashMap<>(); // 조회가 빠름
    int id = 0;

    public void save(String data) {
        db.put(id++, data);
    }

    public void findById(int id) {
        db.get(id);
    }
}
