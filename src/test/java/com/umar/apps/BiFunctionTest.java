package com.umar.apps;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A BiFunction takes two arguments of type T and U and returns an object of type R.
 * A {@link java.util.function.BiFunction} is defined as
 *
 * {@code
 *      interface BiFunction<T, U, R> {
 *          R apply (T t, U u);
 *      }
 * }
 * where,
 * T – Type of the first argument to the function.
 * U – Type of the second argument to the function.
 * R – Type of the result of the function.
 */
public class BiFunctionTest {

    @Test
    void given_two_integer_arguments_BiFunction_returns_sum_of_the_two() {
        BiFunction<Integer, Integer, Integer> summingFunc = Integer::sum;
        Integer result = summingFunc.apply(2,6);
        assertThat(result).isEqualTo(8);
    }

    /**
     * This BiFunction takes two Integer arguments and computes Math.pow
     */
    @Test
    void given_two_integers_BiFunctions_computes_power() {
        BiFunction<Integer, Integer, Double> powerFunc = Math::pow;
        double result = powerFunc.apply(2, 4);
        assertThat(result).isEqualTo(16);
    }

    /**
     * This BiFunction takes two Integer arguments and returns a Double
     * and uses andThen() chain to convert the Double to a String
     */
    @Test
    void given_two_integers_BiFunction_converts_to_Double_andThen_chains_it_to_String() {
        Function<Double, String> toString = Object::toString;
        BiFunction<Integer, Integer, Double> powerFunc = Math::pow;
        String result = powerFunc.andThen(toString).apply(2, 3);
        assertThat(result).isEqualTo("8.0");
    }

    static <T1, T2, R1, R2> R2 convert(T1 t1, T2 t2, BiFunction<T1, T2, R1> func, Function<R1, R2> func2) {
        return func.andThen(func2).apply(t1, t2);
    }
    /**
     * The above BiFunction can be converted into a generic method {@code convert} as above
     */
    @Test
    void given_two_integers_convert_converts_to_Double_andThen_chains_it_to_String() {
        String result = convert(2, 3, (t1, t2) -> Math.pow(t1, t2), r -> String.valueOf(r));
        assertThat(result).isEqualTo("8.0");
    }

    static <T, U, R> List<R> filterList(List<T> list, U condition, BiFunction<T, U, R> func) {
        List<R> result = new ArrayList<>();
        for (T t: list) {
            R apply = func.apply(t, condition);
            if(null != apply) {
                result.add(apply);
            }
        }
        return result;
    }

    @Test
    void given_filterList_method_filters_strings_starting_with_c() {
        List<String> list = List.of("C", "C++", "Ceylon", "Camel", "Go","Java", "C#", "Kotlin");
        List<String> filtered = filterList(list, "C", (l1, condition) -> {
            if(l1.startsWith(condition)) {
                return l1;
            } else {
                return null;
            }
        });
        assertThat(filtered).contains("C", "C++", "Ceylon", "Camel", "Ceylon", "C#");
        assertThat(filtered).doesNotContain("Java", "Kotlin", "Go");
    }
}
