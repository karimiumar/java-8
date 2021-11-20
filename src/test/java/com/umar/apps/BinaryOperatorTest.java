package com.umar.apps;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A {@link java.util.function.BinaryOperator} extends {@link java.util.function.BiFunction}
 *
 * A {@link java.util.function.BinaryOperator} takes two arguments of the same type
 * and returns an objects of the same type as arguments
 *
 * It is defined as {@code
 *
 *      interface BinaryOperator<T> extends BiFunction<T, T, T> {
 *      }
 * }
 */
public class BinaryOperatorTest {

    @Test
    void given_BinaryOperator_of_integers_when_added_then_cumulative_value() {
        BinaryOperator<Integer> intOperator = Integer::sum;
        assertThat(intOperator.apply(2, 3)).isEqualTo(5);
        assertThat(intOperator.apply(5, 6)).isEqualTo(11);
    }

    @Test
    void given_BinaryOperator_simulates_reduce_when_applied_on_List_of_numbers() {
        List<Integer> numbers = List.of(4, 5, 6);
        assertThat(reduce(numbers, 0, Integer::sum)).isEqualTo(15);
    }

    static <T extends Number> T reduce(List<T> list, T initValue, BinaryOperator<T> accumulator) {
        T result = initValue;
        for(T t: list) {
            result = accumulator.apply(result, t);
        }
        return result;
    }


    static Developer find(List<Developer> developers, BinaryOperator<Developer> accumulator) {
        Developer result = null;
        for(Developer developer: developers) {
            if(null == result) {
                result = developer;
            }
            else {
                result = accumulator.apply(result, developer);
            }
        }
        return  result;
    }

    @Test
    void given_List_of_Developers_find_developer_with_max_salary() {
        List<Developer> developers = List.of(
                new Developer("Daniel", BigDecimal.valueOf(330000))
                ,new Developer("Jordan", BigDecimal.valueOf(430000))
                ,new Developer("Spark", BigDecimal.valueOf(530000))
                ,new Developer("Haya", BigDecimal.valueOf(450000))
                ,new Developer("Neil", BigDecimal.valueOf(620000))
                ,new Developer("Zara", BigDecimal.valueOf(650000))
                ,new Developer("Sara", BigDecimal.valueOf(780000))
        );

        Developer developer = find(developers, BinaryOperator.maxBy(Comparator.comparing(Developer::salary)));

        assertThat(developer).isNotNull();
        assertThat(developer.name()).isEqualTo("Sara");
    }

    @Test
    void given_List_of_Developers_find_developer_with_min_salary() {
        List<Developer> developers = List.of(
                new Developer("Daniel", BigDecimal.valueOf(330000))
                ,new Developer("Jordan", BigDecimal.valueOf(430000))
                ,new Developer("Spark", BigDecimal.valueOf(530000))
                ,new Developer("Haya", BigDecimal.valueOf(450000))
                ,new Developer("Neil", BigDecimal.valueOf(620000))
                ,new Developer("Zara", BigDecimal.valueOf(650000))
                ,new Developer("Sara", BigDecimal.valueOf(780000))
        );

        Developer developer = find(developers, BinaryOperator.minBy(Comparator.comparing(Developer::salary)));

        assertThat(developer).isNotNull();
        assertThat(developer.name()).isEqualTo("Daniel");
    }

    record Developer(String name, BigDecimal salary) {
        Developer {
            Objects.requireNonNull(name, "Name is required");
            Objects.requireNonNull(salary, "Salary is required");
        }
    }
}
