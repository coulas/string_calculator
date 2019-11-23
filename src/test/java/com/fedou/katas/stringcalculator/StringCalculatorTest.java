package com.fedou.katas.stringcalculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class StringCalculatorTest {

    private StringCalculator stringCalculator = new StringCalculator();

    @Test
    void empty_string_count_as_0() {
        Assertions.assertEquals(0, stringCalculator.add(""));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 123})
    void single_numbers_should_return_themselves(int input) {
        Assertions.assertEquals(input, stringCalculator.add("" + input));
    }

    @Nested
    class Should_accept_default_delimiters {
        @Test
        void two_numbers_separated_by_comma_should_be_added() {
            Assertions.assertEquals(3, stringCalculator.add("1,2"));
        }

        @Test
        void several_numbers_separated_by_comma_should_be_added() {
            Assertions.assertEquals(6, stringCalculator.add("1,2,3"));
        }

        @Test
        void several_numbers_separated_by_comma_and_or_newline_should_be_added() {
            Assertions.assertEquals(6, stringCalculator.add("1\n2,3"));
        }
    }

    @Nested
    class Should_handle_out_of_range_numbers {
        @Test
        void should_throw_all_negatives() {
            String message = Assertions.assertThrows(IllegalArgumentException.class,
                    () -> stringCalculator.add("1,-2,3,-4,5"))
                    .getMessage();
            Assertions.assertAll(
                    () -> Assertions.assertTrue(message.contains("-2")),
                    () -> Assertions.assertTrue(message.contains("-4"))
            );
        }

        @Test
        void should_ignore_numbers_above_1000() {
            Assertions.assertAll(
                    () -> Assertions.assertEquals(6, stringCalculator.add("1,1234,2,2345,3")),
                    () -> Assertions.assertEquals(999 + 1000, stringCalculator.add("999,1000,1001"))
            );
        }
    }

    @TestFactory
    List<DynamicTest> should_accept_custom_multiple_delimiters() {
        // let's say there is no \n allowed in custom delimiters !
        // let's say there is no empty numbers when delimiter are same with different length (** and ***, input has 1*****2***3**4
        return Arrays.asList(
                buildATestWith("single delimiter with one character", "//;\n1;2", 3),
                buildATestWith("one delimiter with special characters", "//*\n1*2**3", 6),
                buildATestWith("one delimiter with several characters", "//[;;]\n1;;2;;3", 6),
                buildATestWith("one delimiter with several and special characters", "//[***]\n1***2***3", 6),
                buildATestWith("two delimiters with special charcaters", "//[*][%]\n1%2*3", 6),
                buildATestWith("two delimiters and several and special characters", "//[**][%%]\n1%%2**3", 6),
                buildATestWith("two delimiters with character but different length", "//[**][***]\n1***2**3", 6)
        );
    }

    private DynamicTest buildATestWith(String message, String input, int expectedOuput) {
        return DynamicTest.dynamicTest(message, () -> Assertions.assertEquals(expectedOuput, stringCalculator.add(input), message));
    }

}
