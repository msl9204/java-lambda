package chapter4;

import java.util.function.Supplier;

public class SupplierPractice {

    public static void main(String[] args) {
        Supplier<String> myStringSupplier = () -> "hello world!"; // return만 해줌
        System.out.println(myStringSupplier.get());

        Supplier<Double> myRandomSupplier = () -> Math.random();
        printRandomDoubles(myRandomSupplier, 5);

    }

    public static void printRandomDoubles(Supplier<Double> randomSupplier, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(randomSupplier.get());
        }
    }
}
