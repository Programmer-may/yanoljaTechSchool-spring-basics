package com.example.FastcampusSpringBasics.day4;

public class RefactoringDemo {

    int baseFee = 5000;

    void chargeFee(int month) {
        int charge = 0;

        if (isSummer(month)) {
            //charge = getSummerCharge(); -> 이런식으로 표현
        } else {
            //charge = winterRate * baseFee; -> 이런 식으로 표현
        }

//        if (isNotSummer(month)) {
//            charge = winterRate * baseFee;
//            // 겨울 비용 부과하겠다.
//        } else {
//            charge = summerRate * baseFee;
//        }
    }

    private static boolean isSummer(int month) {
        return month > 6 && month < 9;
    }
}
