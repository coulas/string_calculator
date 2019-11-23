package com.fedou.katas.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final String CUSTOM_DELIMITER_PREFIX = "//";
    private String delimiter = ",|\n";

    public int add(final String numbers) {
        String numbersToParse = normalizeNumbersWithCustomDelimiters(numbers);
        return parseNumbers(numbersToParse);
    }

    private String normalizeNumbersWithCustomDelimiters(String numbers) {
        if (numbers.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int customDelimiterEnding = numbers.indexOf('\n');
            String[] customDelimiters = extractDelimiters(numbers, customDelimiterEnding);
            String numbersToParse = extractNumberstoParse(numbers, customDelimiterEnding);
            return normalizeNumberstoParse(numbersToParse, customDelimiters);
        } else {
            return numbers;
        }
    }

    private String extractNumberstoParse(String numbers, int customDelimiterEnding) {
        return numbers.substring(customDelimiterEnding + 1);
    }

    private String normalizeNumberstoParse(String numbersToParse, String[] customDelimiters) {
        for (String customDelimiter : customDelimiters) {
            if (customDelimiter.isEmpty()) {
                continue;
            }
            numbersToParse = Pattern
                    .compile(customDelimiter, Pattern.LITERAL)
                    .matcher(numbersToParse)
                    .replaceAll(",");
        }
        return numbersToParse;
    }

    private String[] extractDelimiters(String numbers, int customDelimiterEnding) {
        String delimiterOnly = numbers.substring(
                CUSTOM_DELIMITER_PREFIX.length(),
                customDelimiterEnding);
        String[] customDelimiters = delimiterOnly.split("\\[|\\]");
        Arrays.sort(customDelimiters, (l, r) -> r.length() - l.length());
        return customDelimiters;
    }

    private int parseNumbers(String numbersToParse) {
        int result = 0;
        List<Integer> negatives = new ArrayList<>();
        for (String number : numbersToParse.split(delimiter)) {
            if (number.isEmpty()) {
                continue;
            }
            int value = Integer.parseInt(number);
            if (value > 1000) {
                continue;
            }
            if (value < 0) {
                negatives.add(value);
            }
            result += value;
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives are not allowed : " + negatives);
        }
        return result;
    }

}
