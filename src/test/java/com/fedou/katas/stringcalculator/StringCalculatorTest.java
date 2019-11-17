package com.fedou.katas.stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
    @Test
    void empty_string_count_as_0() {
        Assertions.assertEquals(0, new StringCalculator().add(""));
    }
}
