package day14;

import common.InputReader;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Advent of code - day 14
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static long firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day14");
        String polymer = inputAsString.get(0);
        Map<String, String> replacements = new HashMap<>();
        for (int i = 2; i < inputAsString.size(); i++) {
            String[] pair = inputAsString.get(i).split("->");
            replacements.put(pair[0].trim(), pair[1].trim());
        }
        for (int j = 0; j < 10; j++) {
            StringBuilder newPolymer = new StringBuilder();
            for (int i = 0; i < polymer.length() - 1; i++) {
                String key = polymer.charAt(i) + "" + polymer.charAt(i + 1);
                if (replacements.containsKey(key)) {
                    newPolymer.append(polymer.charAt(i)).append(replacements.get(key));
                } else {
                    newPolymer.append(polymer.charAt(i));
                }
                if (i == polymer.length() - 2) {
                    newPolymer.append(polymer.charAt(i));
                }
            }
            polymer = newPolymer.toString();
        }

        long min = polymer.chars().boxed().collect(Collectors.toMap(Function.identity(), c -> 1L, Long::sum)).values().stream().min(Comparator.naturalOrder()).orElseThrow();
        long max = polymer.chars().boxed().collect(Collectors.toMap(Function.identity(), c -> 1L, Long::sum)).values().stream().max(Comparator.naturalOrder()).orElseThrow();
        return max - min;
    }

    public static long secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day14");
        String polymer = inputAsString.get(0);
        Map<String, String> replacements = new HashMap<>();
        for (int i = 2; i < inputAsString.size(); i++) {
            String[] pair = inputAsString.get(i).split("->");
            replacements.put(pair[0].trim(), pair[1].trim());
        }

        Map<String, Long> pairs = new HashMap<>();
        Map<String, Long> letterCounts = new HashMap<>();
        for (int i = 0; i < polymer.length() - 1; i++) {
            String pair = polymer.charAt(i) + "" + polymer.charAt(i + 1);
            pairs.put(pair, pairs.getOrDefault(pair, 0L) + 1);

            letterCounts.put(pair.charAt(0) + "", letterCounts.getOrDefault(pair.charAt(0) + "", 0L) - 1L);
        }

        for (int i = 0; i < 40; i++) {
            for (Map.Entry<String, Long> entry : Map.copyOf(pairs).entrySet()) {
                String replacement = replacements.get(entry.getKey());
                if (replacement != null) {
                    String firstNewPair = entry.getKey().charAt(0) + replacement;
                    String secondNewPair = replacement + entry.getKey().charAt(1);

                    pairs.put(firstNewPair, pairs.getOrDefault(firstNewPair, 0L) + entry.getValue());
                    pairs.put(secondNewPair, pairs.getOrDefault(secondNewPair, 0L) + entry.getValue());
                    pairs.put(entry.getKey(), pairs.get(entry.getKey()) - entry.getValue());

                    letterCounts.put(replacement, letterCounts.getOrDefault(replacement, 0L) - entry.getValue());
                }
            }
        }

        for (Map.Entry<String, Long> entry : pairs.entrySet()) {
            letterCounts.put(entry.getKey().charAt(0) + "", letterCounts.getOrDefault(entry.getKey().charAt(0) + "", 0L) + entry.getValue());
            letterCounts.put(entry.getKey().charAt(1) + "", letterCounts.getOrDefault(entry.getKey().charAt(1) + "", 0L) + entry.getValue());
        }
        long min = letterCounts.values().stream().min(Comparator.naturalOrder()).orElseThrow();
        long max = letterCounts.values().stream().max(Comparator.naturalOrder()).orElseThrow();
        return max - min;
    }
}
