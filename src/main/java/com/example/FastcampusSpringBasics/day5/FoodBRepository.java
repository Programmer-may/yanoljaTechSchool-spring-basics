package com.example.FastcampusSpringBasics.day5;

import org.springframework.stereotype.Repository;

@Repository
public class FoodBRepository implements FoodRepository{

    FoodBRepository() {
        System.out.println("BRepository");
    }

    public void test() {
        System.out.println("BRepositoy의 Test메소드 ");
    }
}
