package chapter3;

import java.util.function.Function;

public class Adder implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer x) {
        return x + 10;
    }

    public static void main(String[] args) {
        Function<Integer, Integer> myAdder = new Adder();
        System.out.println(myAdder.apply(5));
    }
}
