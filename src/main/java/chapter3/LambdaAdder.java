package chapter3;

import java.util.function.Function;

public class LambdaAdder {

    public static void main(String[] args) {
        Function<Integer, Integer> myAdder_default = (Integer x) -> {
            return x + 10;
        };

        Function<Integer, Integer> myAdder = x -> x + 10;

        System.out.println(myAdder.apply(3));
    }
}
