package learnstream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by useheart on 2019-05-19
 *
 * @author useheart
 */
public class CountLongWorlds {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("/Users/useheart/Applications/IDEA/JarResearch/src/main/java/learnstream/alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        long count = 0;
        for (String word : words) {
            if (word.length() > 12) {
                count++;
            }
        }
        Stream<String> filterStream = words.stream().filter(w -> w.length() > 12);
        Stream<String> mapLowerCaseStream = words.stream().map(String::toLowerCase);
        Stream<String> firstLetters = words.stream().map(s -> s.substring(0, 1));
        IntStream.range(1, 20).forEach(System.out::println);
        Stream.generate(Math::random).limit(100);
        IntStream.range(1, 20).skip(10).forEach(System.out::println);
        Stream.concat(letters("Hello"), letters("World")).forEach(System.out::println);

        Stream.of("merrily", "merrily", "gently").distinct().forEach(System.out::println);

        words.stream().sorted(Comparator.comparingInt(String::length));
        Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20)
                .toArray();

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        System.out.println("largest: " + largest.orElse(""));
        words.stream().parallel().filter(s -> s.startsWith("Q")).findAny();
        Optional<String> optionalString = Optional.of("I LOVE YOU");
        System.out.println(optionalString.orElse(""));
        System.out.println(optionalString.orElseGet(() -> Locale.getDefault().getDisplayName()));
        optionalString.orElseThrow(IllegalArgumentException::new);
        optionalString.map(String::toLowerCase);
        Optional.ofNullable("I LOVE YOU");
        System.out.println(count);
    }

    private static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }
}
