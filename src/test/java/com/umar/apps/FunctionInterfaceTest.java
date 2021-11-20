package com.umar.apps;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
  * A {@link Function} interface takes an argument of type T and returns a result of type R.
    Its defined as{@code
        @FunctionalInterface
        public interface Function<T, R> {
            R apply(T t);
        }
    }
 *
 */
public class FunctionInterfaceTest {

    @Test
    void given_a_Function_of_String_returns_its_length() {
        Function<String, Integer> stringLengthFunc = String::length;
        Integer apply = stringLengthFunc.apply("My Length please");
        assertThat(apply).isEqualTo(16);
    }

    /**
     * Example demonstration function chaining {@code apply().andThen() }
     */
    @Test
    void given_a_Function_doubling_the_length_of_String() {
        Function<String, Integer> stringLength = String::length;
        Function<Integer, Integer> doubleIt = x -> x * 2;
        Integer apply = stringLength.andThen(doubleIt).apply("My Length please");
        assertThat(apply).isEqualTo(32);
    }

    /**
     * A Function to convert a {@link java.util.List} to {@link java.util.Map}
     */
    @Test
    void given_a_List_when_apply_convertToMap_converts_list_to_Map() {
        List<String> languages = List.of("Java", "C++", "NodeJS", "VueJS", "C#", "Kotlin", "Ceylon");
        Map<String, Integer> map = convertListToMap(languages, String::length);
        assertThat(map).isNotEmpty();
        assertThat(map).containsEntry("Java", 4);
        assertThat(map).containsKeys("Java", "C#", "Ceylon", "NodeJS", "Kotlin", "VueJS", "C++");
        assertThat(map).containsValues(4,3,6,5,2,6,6);
    }


    private <T, R> Map<T, R> convertListToMap(List<T> list, Function<T, R> function) {
        Map<T, R> result = new HashMap<>();
        list.forEach(l -> result.put(l, function.apply(l)));
        return result;
    }


}
