package chapter3;

import java.util.function.BiFunction;

public class BiFunctionPractice {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> default_add = (Integer x, Integer y) -> {
            return x + y;
        };

        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;

        int result = add.apply(3, 5);
        System.out.println(result);
    }
}
