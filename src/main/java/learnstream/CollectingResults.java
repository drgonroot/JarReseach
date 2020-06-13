package learnstream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by useheart on 2019-05-28
 *
 * @author useheart
 */
public class CollectingResults {

    public static Stream<String> noVowels() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("/Users/useheart/Applications/IDEA/JarResearch/src/main/java/learnstream/alice30.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.println(label + ": " + set.getClass().getName());
        System.out.println("[" +
                set.stream().limit(10).map(Object::toString)
                        .collect(Collectors.joining(",")) + "]");
        System.out.println(set.stream().limit(10)
                .map(Objects::toString).collect(Collectors.joining(",", "[", "]")));
    }

    public static void main(String[] args) throws IOException {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10)
                .iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        // Note is's an Object[] array
        System.out.println("Object array:" + Arrays.toString(numbers));
        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number" + number);
            System.out.println("The following statement throws an exception:");
            // throw exception
            Integer[] numbers2 = (Integer[]) numbers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10)
                .toArray(Integer[]::new);
        // Note it's an Integer[] array
        System.out.println("Integer array: " + Arrays.toString(numbers3));

        Set<String> noVowelSet = noVowels()
                .collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVowels().limit(10)
                .collect(Collectors.joining());
        System.out.println("joining:" + result);
        result = noVowels().limit(10)
                .collect(Collectors.joining(","));
        System.out.println("Joining with commas" + result);

        IntSummaryStatistics summary = noVowels().collect(
                Collectors.summarizingInt(String::length));
        double averageWorldLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average word length:" + averageWorldLength);
        System.out.println("Max word length: " + maxWordLength);
        System.out.println("forEach:");
        noVowels().limit(10).forEach(System.out::println);
        System.out.println();
    }
}
