package day10;

import common.InputReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Advent of code - day 10
 */
public class Solution {

    private static final Map<Character, Character> openAndCloseCharacters = Map.of(
            '(', ')',
            '[', ']',
            '{', '}',
            '<', '>'
    );
    private static final Map<Character, Long> endingCharactersAndValues = Map.of(
            ')', 3L,
            ']', 57L,
            '}', 1197L,
            '>', 25137L
    );

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day10");
        int maxX = inputAsString.size();
        int maxY = inputAsString.get(0).toCharArray().length;

        char[][] input = new char[maxX][maxY];

        for (int i = 0; i < inputAsString.size(); i++) {
            char[] singleLine = inputAsString.get(i).toCharArray();
            input[i] = singleLine;
        }

        int result = 0;
        for (int i = 0; i < maxX; i++) {
            char[] singleLine = input[i];
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : singleLine) {
                if (openAndCloseCharacters.containsKey(c)) {
                    stack.push(c);
                } else {
                    if ((c == ')' && stack.peek() == '(') || (c == '}' && stack.peek() == '{') || (c == ']' && stack.peek() == '[') || (c == '>' && stack.peek() == '<')) {
                        stack.pop();
                    } else {
                        result += endingCharactersAndValues.get(c);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static long secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day10");
        int maxX = inputAsString.size();
        int maxY = inputAsString.get(0).toCharArray().length;

        char[][] input = new char[maxX][maxY];

        for (int i = 0; i < inputAsString.size(); i++) {
            char[] singleLine = inputAsString.get(i).toCharArray();
            input[i] = singleLine;
        }

        List<char[]> illegalLines = new ArrayList<>();
        for (int i = 0; i < maxX; i++) {
            char[] singleLine = input[i];
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : singleLine) {
                if (openAndCloseCharacters.containsKey(c)) {
                    stack.push(c);
                } else {
                    if ((c == ')' && stack.peek() == '(') || (c == '}' && stack.peek() == '{') || (c == ']' && stack.peek() == '[') || (c == '>' && stack.peek() == '<')) {
                        stack.pop();
                    } else {
                        illegalLines.add(singleLine);
                        break;
                    }
                }
            }
        }
        List<char[]> legalLines = new ArrayList<>();
        for (int i = 0; i < maxX; i++) {
            if (!illegalLines.contains(input[i])) {
                legalLines.add(input[i]);
            }
        }

        List<List<Character>> charsToComplete = new ArrayList<>();
        for (char[] line : legalLines) {
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : line) {
                if (openAndCloseCharacters.containsKey(c)) {
                    stack.push(c);
                } else {
                    if ((c == ')' && stack.peek() == '(') || (c == '}' && stack.peek() == '{') || (c == ']' && stack.peek() == '[') || (c == '>' && stack.peek() == '<')) {
                        stack.pop();
                    }
                }
            }
            List<Character> toComplete = stack.stream()
                    .map(openAndCloseCharacters::get)
                    .toList();

            charsToComplete.add(toComplete);
        }
        List<Long> results = new ArrayList<>();
        for (List<Character> chars : charsToComplete) {
            long partialResult = 0L;
            for (Character c : chars) {
                partialResult *= 5;
                if (c == ')') partialResult += 1L;
                if (c == ']') partialResult += 2L;
                if (c == '}') partialResult += 3L;
                if (c == '>') partialResult += 4L;
            }
            results.add(partialResult);
        }
        List<Long> sortedResults = results.stream().sorted().toList();
        return sortedResults.get(sortedResults.size()/2);
    }
}
