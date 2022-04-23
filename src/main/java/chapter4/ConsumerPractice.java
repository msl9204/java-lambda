package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerPractice {

    public static void main(String[] args) {
        Consumer<String> myStringConsumer = (str) -> System.out.println(str);

        myStringConsumer.accept("hello");
        myStringConsumer.accept("world!");

        List<Integer> integerInputs = Arrays.asList(4, 2, 3);
        Consumer<Integer> myIntegerProcessor = (Integer x) -> System.out.println("Processing integer " + x);
        Consumer<Integer> myDifferentIntegerProcessor = (Integer x) -> System.out.println("Processing integer in different way " + x);
//        process(integerInputs, myDifferentIntegerProcessor);

        Consumer<Double> myDoubleProcessor = x -> System.out.println("Processing Double " + x);
        List<Double> doubleInputs = Arrays.asList(4.0, 2.0, 3.0);

        process(doubleInputs, myDoubleProcessor);
    }

    public static <T> void process(List<T> inputs, Consumer<T> processor) {
        for (T input : inputs) {
            processor.accept(input);
        }
    }

}
