package com.fedou.katas.stringcalculator;

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
        for (String number : numbersToParse.split(delimiter)) {
            result += Integer.parseInt(number);
        }
        return result;
    }
}
