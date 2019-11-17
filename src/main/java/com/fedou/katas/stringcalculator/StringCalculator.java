package com.fedou.katas.stringcalculator;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        int result = 0;
        for (String number : numbers.split(",")) {
            result += Integer.parseInt(number);
        }
        return result;
    }
}
