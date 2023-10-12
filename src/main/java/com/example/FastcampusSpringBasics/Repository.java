package com.example.FastcampusSpringBasics;

public interface Repository {
    abstract void save(String data);
    abstract void findById(int id);
}
