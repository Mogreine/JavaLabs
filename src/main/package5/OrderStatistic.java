package main.package5;

import java.util.TreeSet;
import java.util.stream.Stream;

public class OrderStatistic {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        Stream.of(set).forEach(s -> System.out.print(s + " "));
    }

}
