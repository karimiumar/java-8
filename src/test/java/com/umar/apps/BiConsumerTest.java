package com.umar.apps;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

/**
 * A {@link java.util.function.BiConsumer} takes two arguments and returns nothing
 * A {@link java.util.function.BiConsumer} is defined as
 *
 * {@code
 *      interface BiConsumer<T, U> {
 *          void accept(T t, U u);
 *      }
 * }
 */
public class BiConsumerTest {

    @Test
    void given_BiConsumer_of_integers_when_add_then_sum() {
        BiConsumer<Integer, Integer> addInts = (x, y) -> System.out.printf("sum=%d\n", x + y);
        addInts.accept(3, 9);
    }

    @Test
    void given_HighOrderFunction_operation_when_sum_of_numbers_then_prints_sum_of_numbers() {
        operation(5, 7, (x, y) -> System.out.printf("%d + %d = %d\n",x, y, x + y));
    }

    @Test
    void given_HighOrderFunction_operation_when_subtraction_of_numbers_then_prints_diff_of_numbers() {
        operation(5, 7, (x, y) -> System.out.printf("%d - %d = %d\n",x, y, x - y));
    }

    @Test
    void given_HighOrderFunction_operation_when_multiply_of_numbers_then_prints_product_of_numbers() {
        operation(5, 7, (x, y) -> System.out.printf("%d x %d = %d\n",x, y, x * y));
    }

    @Test
    void given_HighOrderFunction_operation_when_division_of_numbers_then_prints_quotient_of_numbers() {
        operation(15, 7, (x, y) -> System.out.printf("%d / %d = %d\n",x, y, x / y));
    }

    @Test
    void given_HighOrderFunction_operation_when_mod_of_numbers_then_prints_remainder_of_numbers() {
        operation(15, 7, (x, y) -> System.out.printf("%d %% %d = %d\n",x, y, x % y));
    }

    @Test
    void given_HighOrderFunction_operation_when_addition_of_strings_then_prints_concatenation_of_strings() {
        operation("Node", ".js", (x, y) -> System.out.println(x + y));
    }

    @Test
    void given_HighOrderFunction_operation_when_concat_of_strings_then_prints_concatenation_of_strings() {
        operation("Node", ".js", (x, y) -> System.out.println(x.concat(y)));
    }

    /**
     * Demonstrates a High Order Function
     */
    static <T> void operation(T a, T b, BiConsumer<T, T> consumer) {
        consumer.accept(a, b);
    }
}
