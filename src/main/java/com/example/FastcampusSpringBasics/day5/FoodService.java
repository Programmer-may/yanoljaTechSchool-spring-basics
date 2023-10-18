package com.example.FastcampusSpringBasics.day5;

import com.example.FastcampusSpringBasics.day5.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;
//
//    FoodService(FoodRepository foodRepository) {
//        this.foodRepository = foodRepository;
//    }

    void test() {
        foodRepository.test();
    }
}
