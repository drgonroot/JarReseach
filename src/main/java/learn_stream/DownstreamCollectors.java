package learn_stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by useheart on 2019-05-30
 *
 * @author useheart
 */
public class DownstreamCollectors {

    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocalSet = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
        System.out.println("countryToLocalSet:" + countryToLocalSet);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
        System.out.println("countryToLocaleCounts:" + countryToLocaleCounts);

        Stream<City> citys = readCities("cities.txt");
        Map<String, Integer> stateToCityPopulation = citys.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation:" + stateToCityPopulation);
        citys = readCities("cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = citys.collect(Collectors.groupingBy(City::getState, Collectors.mapping(City::getName, Collectors.maxBy(Comparator.comparing(String::length)))));
        System.out.println("stateToLongestCityName: " + stateToLongestCityName);
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry, Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
        System.out.println("countryToLanguages:" + countryToLanguages);

        citys = readCities("cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = citys.collect(Collectors.groupingBy(City::getState, Collectors.summarizingInt(City::getPopulation)));
        System.out.println("stateToCityPopulationSummary:" + stateToCityPopulationSummary);
        System.out.println(stateToCityPopulationSummary.get("NY"));

        citys = readCities("cities.txt");
        Map<String, String> statToCityNames = citys.collect(Collectors.groupingBy(City::getState, Collectors.reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)));
        System.out.println("statToCityNames:" + statToCityNames);
        citys = readCities("cities.txt");
        statToCityNames = citys.collect(Collectors.groupingBy(City::getState, Collectors.mapping(City::getName, Collectors.joining(","))));
        System.out.println("statToCityNames:" + statToCityNames);

        IntStream.range(1, 100);
    }
}
