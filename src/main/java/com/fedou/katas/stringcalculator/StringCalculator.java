package com.fedou.katas.stringcalculator;

public class StringCalculator {

    public static final String CUSTOM_DELIMITER_PREFIX = "//";
    private String delimiter = ",|\n";

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (numbers.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int customDelimiterEnding = numbers.indexOf('\n');
            String customDelimiter = numbers.substring(
                    CUSTOM_DELIMITER_PREFIX.length(),
                    customDelimiterEnding);
            delimiter=customDelimiter;
            numbers = numbers.substring(customDelimiterEnding+1);
        }
        int result = 0;
        for (String number : numbers.split(delimiter)) {
            result += Integer.parseInt(number);
        }
        return result;
    }
}
