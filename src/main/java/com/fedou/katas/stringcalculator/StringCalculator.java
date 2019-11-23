package com.fedou.katas.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static final String CUSTOM_DELIMITER_PREFIX = "//";
    private String delimiter = ",|\n";

    public int add(final String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String numbersToParse;
        if (numbers.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int customDelimiterEnding = numbers.indexOf('\n');
            String customDelimiter = numbers.substring(
                    CUSTOM_DELIMITER_PREFIX.length(),
                    customDelimiterEnding);
            delimiter=customDelimiter;
            numbersToParse = numbers.substring(customDelimiterEnding+1);
        } else {
            numbersToParse = numbers;
        }
        int result = 0;
        List<Integer> negatives = new ArrayList<>();
        for (String number : numbersToParse.split(delimiter)) {
            int value = Integer.parseInt(number);
            if (value > 1000) {
                continue;
            }
            if (value<0) {
                negatives.add(value);
            }
            result += value;
        }
        if (negatives.isEmpty()) {
            return result;
        } else {
            throw new IllegalArgumentException("negatives are not allowed : "+negatives);
        }
    }
}
