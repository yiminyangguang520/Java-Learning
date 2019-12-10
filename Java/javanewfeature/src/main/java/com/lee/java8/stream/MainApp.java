package com.lee.java8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author litz-a
 */
public class MainApp {

  public static void main(String[] args) {

    Pattern p = Pattern.compile("\\W");

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    List<String> result = numbers.stream()
      .filter(i -> (i % 2) == 0).map(i -> "[" + i + "]")
      .collect(Collectors.toList());
    System.out.println(result);

    int[] arr_numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    IntStream streamArr = Arrays.stream(arr_numbers);
    System.out.println(streamArr.sum());

    IntStream streamRange = IntStream.range(0, 10);
    System.out.println(streamRange.sum());

    Stream<String> streamOf = Stream.of("java", "sample", "approach", ".com");
    streamOf.forEach(System.out::print);
    System.out.println();

    Stream<Integer> streamIter = Stream.iterate(0, i -> i + 2).limit(10);
    streamIter.forEach(i -> System.out.print(i.toString() + ' '));

    Stream<String> emptyStream = Stream.empty();
    System.out.println("\n" + emptyStream.toArray().length);

    Stream<Double> streamGen = Stream.generate(Math::random).limit(10);
    streamGen.forEach(i -> System.out.print(i.toString() + ' '));
    System.out.println();

    try {
      long numberWords = java.nio.file.Files.lines(Paths.get("file.txt"), Charset.defaultCharset())
          .flatMap(line -> Arrays.stream(line.split(" ."))).distinct().count();
      System.out.println(numberWords);
    } catch (IOException e) {
      System.out.println("IOException when reading or getting data from file");
    }

    String name = "Java Sample Approach";
    Stream<String> streamWords = p.splitAsStream(name);
    streamWords.forEach(System.out::print);
    System.out.println();

    List<Integer> newNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Stream<Integer> lazyStream = newNumbers.stream().filter(i -> {
      System.out.println("filter: [" + i + "]");
      return (i % 2) == 0;
    }).map(i -> {
      System.out.println("map: [" + i + "]");
      return i;
    });

    for (int i = 1; i <= 3; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("running... " + i + " sec");
    }

    lazyStream.collect(Collectors.toList());

    // filter
    List<Integer> ftnumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Stream<Integer> ftstream = ftnumbers.stream().filter(i -> (i % 2) == 0);
    ftstream.forEach(System.out::print);
    System.out.println();

    // map
    Stream<String> mapstream = ftnumbers.stream().map(i -> i.toString());
    mapstream.forEach(System.out::print);
    System.out.println();

    // map
    class Foo {

      public String name;
      public List<String> bars;

      public Foo(String name, List<String> bars) {
        super();
        this.name = name;
        this.bars = bars;
      }
    }

    List<Foo> fooList = new ArrayList<>();
    List<String> l1 = Arrays.asList("Java", "Sample", "Approach");
    List<String> l2 = Arrays.asList("Java Tecnology", "Spring Framework", "Sample Code");
    fooList.add(new Foo("foo1", l1));
    fooList.add(new Foo("foo2", l2));

    Stream<String> fooStream = fooList.stream().flatMap(foo -> {
      System.out.println("-- Foo: " + foo.name);
      return foo.bars.stream();
    });
    fooStream.forEach(System.out::println);

    // map
    List<Integer> distnumbers = Arrays.asList(1, 1, 2, 3, 3, 4, 5, 6, 6);
    Stream<Integer> diststream = distnumbers.stream().distinct();
    diststream.forEach(System.out::print);
    System.out.println();

    // sorted
    List<Integer> sortnumbers = Arrays.asList(8, 9, 2, 5, 3, 4, 5, 6, 6);
    Stream<Integer> sortstream = sortnumbers.stream().sorted();
    sortstream.forEach(System.out::print);
    System.out.println();

    // sorted
    List<Integer> limnumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Stream<Integer> limstream = limnumbers.stream().limit(5);
    limstream.forEach(System.out::print);
    System.out.println();

    // forEach
    Stream<String> streamSource = Stream.of("java", "sample", "approach", ".com");
    streamSource.forEach(System.out::print);
    // this will throw an exception
    // streamSource.forEach(System.out::print);
    System.out.println();

    // toArray
    Object[] objects = Stream.of(1, 2, 3, 4, 5).toArray();
    String[] upstringArr = (String[]) Stream.of("java", "sample", "approach", ".com").map(s -> s.toUpperCase())
        .toArray(String[]::new);

    // matching
    boolean isAllNumbersLargerThanFive = numbers.stream()
        .allMatch(i -> i > 5);
    System.out.println(isAllNumbersLargerThanFive); // false

    boolean hasNumberLargerThanFive = numbers.stream()
        .anyMatch(i -> i > 5);
    System.out.println(hasNumberLargerThanFive); // true

    boolean isNoneNumberLargerThanTen = numbers.stream()
        .noneMatch(i -> i > 10);
    System.out.println(isNoneNumberLargerThanTen); // true

    // collect
    List<Customer> customers = Arrays.asList(
        new Customer("Jack", "Smith"),
        new Customer("Adam", "Johnson"),
        new Customer("David", "Green"),
        new Customer("Robert", "Green"));

    List<Customer> filterLastName = customers.stream().filter(c -> c.getLastName().startsWith("Green"))
        .collect(Collectors.toList());

    System.out.println(filterLastName);
    // [Customer[firstName='David', lastName='Green'],
    // Customer[firstName='Robert', lastName='Green']]

    Map<String, List<Customer>> groupByLastName = customers.stream()
        .collect(Collectors.groupingBy(c -> c.getLastName()));

    groupByLastName.forEach(
        (lastName, customer) -> System.out.println(lastName + ": " + customer));
    // Johnson: [Customer[firstName='Adam', lastName='Johnson']]
    // Smith: [Customer[firstName='Jack', lastName='Smith']]
    // Green: [Customer[firstName='David', lastName='Green'], Customer[firstName='Robert', lastName='Green']]

    Map<String, String> lastNameMap = customers.stream()
        .collect(Collectors.toMap(c -> "lastName " + c.getLastName(), c -> c.getFirstName(), (firstName1, firstName2) -> firstName1 + "|" + firstName2));

    System.out.println(lastNameMap);
    // {lastName Green=David|Robert, lastName Johnson=Adam, lastName Smith=Jack}

    String joining = customers.stream().filter(c -> c.getLastName().contains("Green")).map(c -> c.getFirstName())
        .collect(Collectors.joining(", ", "In Customer Database: [", "] have the same lastName Green."));

    System.out.println(joining);
    // In Customer Database: David, Robert have the same lastName Green.

    //List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    numbers.stream()
        .reduce((i1, i2) -> i1 > i2 ? i1 : i2)
        .ifPresent(i -> System.out.println("max: " + i)); // max: 9

    Integer total = numbers.stream().reduce(0, (i1, i2) -> i1 + i2);
    System.out.println("total: " + total); // total: 45

    OptionalInt minimum = IntStream.of(1, 2, 3).min();
    System.out.println("min: " + minimum); // min: OptionalInt[1]

    Optional<String> lastName = Stream.of("A", "B", "C").max(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    });
    System.out.println(lastName); // Optional[C]
  }
}
