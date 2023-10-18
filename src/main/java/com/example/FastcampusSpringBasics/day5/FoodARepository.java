package com.example.FastcampusSpringBasics.day5;

import org.springframework.stereotype.Repository;


public class FoodARepository implements FoodRepository{

    FoodARepository() {
        System.out.println("ARepository");
    }


    public void test() {
        System.out.println("BRepositoy의 Test메소드 ");
    }
}
