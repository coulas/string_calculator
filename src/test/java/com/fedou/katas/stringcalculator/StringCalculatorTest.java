package com.fedou.katas.stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StringCalculatorTest {
    @Test
    void empty_string_count_as_0() {
        Assertions.assertEquals(0, new StringCalculator().add(""));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 1234})
    void single_numbers_should_return_themselves(int input) {
        Assertions.assertEquals(input, new StringCalculator().add("" + input));
    }

    @Test
    void two_numbers_separated_by_comma_should_be_added() {
        Assertions.assertEquals(3, new StringCalculator().add("1,2"));
    }

    @Test
    void several_numbers_separated_by_comma_should_be_added() {
        Assertions.assertEquals(6, new StringCalculator().add("1,2,3"));
    }

    @Test
    void several_numbers_separated_by_comma_and_or_newline_should_be_added() {
        Assertions.assertEquals(6, new StringCalculator().add("1\n2,3"));
    }

}
