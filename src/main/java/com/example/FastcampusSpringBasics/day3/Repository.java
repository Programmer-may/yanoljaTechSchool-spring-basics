package com.example.FastcampusSpringBasics.day3;

public interface Repository {
    abstract void save(String data);
    abstract void findById(int id);
}
