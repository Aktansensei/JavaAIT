import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExersize {
    public static void main(String[] args) {
        // 1
        MathOperation addition = (a,b) -> System.out.println(a+b);
        MathOperation subtraction = (a,b) -> System.out.println(a-b);
        MathOperation multiplication = (a,b) -> System.out.println(a*b);
        MathOperation division = (a,b) -> System.out.println(a/b);

        addition.operate(5, 6);
        subtraction.operate(5, 6);
        multiplication.operate(5, 6);
        division.operate(12, 6);


        // 2
        List<Integer> numbers = Arrays.asList(10, 15, 22, 33, 40, 55);
        List<Integer> result = filterNumbers(numbers, (num) -> num % 2 != 0);
        System.out.println(result);


        // 3
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.sort((a,b) -> b.compareTo(a));
        System.out.println(names);

        // 4
        List<String> words = Arrays.asList("hello", "java", "lambda");
        changeWords(words, w -> new StringBuilder(w.toUpperCase()).reverse().toString());
        System.out.println(words);

        // 5
        List<String> cities = Arrays.asList("New York", "London", "Tokyo", "Berlin");
        cities.forEach(city -> System.out.println(city));

        // 6
        cities.forEach(System.out::println);

        // 7
        int a = 25, b = 40;
        BiFunction<Integer, Integer, Integer> maxFunction = Math::max;
        BiFunction<Integer, Integer, Integer> minFunction = Math::min;

        System.out.println("Max: " + maxFunction.apply(a, b));
        System.out.println("Min: " + minFunction.apply(a, b));
    }


    public static List<Integer> filterNumbers(List<Integer> numbers, Predicate<Integer> condition) {
        List<Integer> result = new ArrayList<>();
        for(Integer num: numbers) {
            if(condition.test(num)) {
                result.add(num);
            }
        }
        return result;
    }
    public static void changeWords(List<String> words, Function<String, String> foo) {
        for(int i = 0; i < words.size(); i++) {
            words.set(i, foo.apply(words.get(i)));
        }
    }


}

interface MathOperation {
    void operate(int a, int b);
}

