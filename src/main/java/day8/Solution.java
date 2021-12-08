package day8;

import common.InputReader;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Advent of code - day 8
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<String> numbers = new InputReader().readInput("input_day8").stream()
                .map(line -> line.split("\\| ")[1])
                .map(line -> line.split(" "))
                .flatMap(Arrays::stream)
                .toList();

        int result = 0;
        for (String number : numbers) {
            if (number.length() == 2 || number.length() == 4 || number.length() == 3 || number.length() == 7) {
                result++;
            }
        }

        return result;
    }

    public static int secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day8");

        List<List<String>> lines = inputAsString.stream()
                .map(line -> line.split("\\| ")[0])
                .map(line -> Arrays.asList(line.split(" ")))
                .toList();

        List<List<String>> codes = inputAsString.stream()
                .map(line -> line.split("\\| ")[1])
                .map(line -> Arrays.asList(line.split(" ")))
                .toList();

        int result = 0;
        for (int i = 0; i < inputAsString.size(); i++) {
            List<String> lineNumbers = lines.get(i);
            String segmentA = determineSegmentA(lineNumbers);
            String segmentC = determineSegmentC(lineNumbers);
            String segmentF = determineSegmentF(lineNumbers, segmentC);
            String segmentD = determineSegmentD(lineNumbers, segmentC);
            String segmentB = determineSegmentB(lineNumbers, List.of(segmentC, segmentD, segmentF));
            String segmentE = determineSegmentE(lineNumbers, List.of(segmentC, segmentD));
            String segmentG = determineSegmentG(lineNumbers, List.of(segmentA, segmentB, segmentC, segmentD, segmentE, segmentF));
            Map<String, String> segmentsMapping = Map.of(
                    segmentA, "A",
                    segmentB, "B",
                    segmentC, "C",
                    segmentD, "D",
                    segmentE, "E",
                    segmentF, "F",
                    segmentG, "G"
            );


            List<String> codeNumbers = codes.get(i);
            StringBuilder resultCode = new StringBuilder();
            for (String c : codeNumbers) {
                resultCode.append(determineSingleNumber(segmentsMapping, c));
            }
            result += Integer.parseInt(resultCode.toString());
        }
        return result;
    }

    static String determineSingleNumber(Map<String, String> mapping, String code) {
        List<Character> codeChars = code.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        if (codeChars.size() == 2) {
            return "1";
        }
        if (codeChars.size() == 3) {
            return "7";
        }
        if (codeChars.size() == 4) {
            return "4";
        }
        if (codeChars.size() == 7) {
            return "8";
        }

        List<String> activeSegments = new ArrayList<>();
        for (Character c : codeChars) {
            activeSegments.add(mapping.get(c.toString()));
        }
        List<String> sortedActiveSegments = activeSegments.stream().sorted().toList();
        List<String> activeSegmentsFor0 = List.of("A", "B", "C", "E", "F", "G");
        List<String> activeSegmentsFor2 = List.of("A", "C", "D", "E", "G");
        List<String> activeSegmentsFor3 = List.of("A", "C", "D", "F", "G");
        List<String> activeSegmentsFor5 = List.of("A", "B", "D", "F", "G");
        List<String> activeSegmentsFor6 = List.of("A", "B", "D", "E", "F", "G");
        List<String> activeSegmentsFor9 = List.of("A", "B", "C", "D", "F", "G");

        if (ListUtils.isEqualList(activeSegmentsFor0, sortedActiveSegments)) {
            return "0";
        }
        if (ListUtils.isEqualList(activeSegmentsFor2, sortedActiveSegments)) {
            return "2";
        }
        if (ListUtils.isEqualList(activeSegmentsFor3, sortedActiveSegments)) {
            return "3";
        }
        if (ListUtils.isEqualList(activeSegmentsFor5, sortedActiveSegments)) {
            return "5";
        }
        if (ListUtils.isEqualList(activeSegmentsFor6, sortedActiveSegments)) {
            return "6";
        }
        if (ListUtils.isEqualList(activeSegmentsFor9, sortedActiveSegments)) {
            return "9";
        }
        return null;
    }

    static String determineSegmentA(List<String> numbers) {
        List<Character> one = numbers.stream().filter(n -> n.length() == 2).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> seven = numbers.stream().filter(n -> n.length() == 3).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        for (Character s : seven) {
            if (!one.contains(s)) {
                return s.toString();
            }
        }
        return "";
    }

    static String determineSegmentC(List<String> numbers) {
        List<String> sixSegmentsDigits = numbers.stream().filter(n -> n.length() == 6).toList();
        List<Character> one = numbers.stream().filter(n -> n.length() == 2).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> allSegmenst = numbers.stream().filter(n -> n.length() == 7).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        List<Character> difference = new ArrayList<>();
        List<Character> first = sixSegmentsDigits.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> second = sixSegmentsDigits.get(1).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> third = sixSegmentsDigits.get(2).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        difference.add(ListUtils.subtract(allSegmenst, first).get(0));
        difference.add(ListUtils.subtract(allSegmenst, second).get(0));
        difference.add(ListUtils.subtract(allSegmenst, third).get(0));

        for (Character c : difference) {
            if (one.contains(c)) {
                return c.toString();
            }
        }
        return "";
    }

    static String determineSegmentF(List<String> numbers, String segmentF) {
        List<Character> one = numbers.stream().filter(n -> n.length() == 2).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        for (Character c : one) {
            if (!segmentF.equals(c.toString())) {
                return c.toString();
            }
        }
        return "";
    }

    static String determineSegmentD(List<String> numbers, String segmentC) {
        List<String> sixSegmentsDigits = numbers.stream().filter(n -> n.length() == 6).toList();
        List<Character> four = numbers.stream().filter(n -> n.length() == 4).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> allSegments = numbers.stream().filter(n -> n.length() == 7).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        List<Character> difference = new ArrayList<>();
        List<Character> first = sixSegmentsDigits.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> second = sixSegmentsDigits.get(1).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> third = sixSegmentsDigits.get(2).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        difference.add(ListUtils.subtract(allSegments, first).get(0));
        difference.add(ListUtils.subtract(allSegments, second).get(0));
        difference.add(ListUtils.subtract(allSegments, third).get(0));

        for (Character c : difference) {
            if (four.contains(c) && !segmentC.equals(c.toString())) {
                return c.toString();
            }
        }
        return "";
    }

    static String determineSegmentB(List<String> numbers, List<String> segments) {
        List<Character> four = numbers.stream().filter(n -> n.length() == 4).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        return ListUtils.subtract(four.stream().map(Object::toString).toList(), segments).get(0);
    }

    static String determineSegmentE(List<String> numbers, List<String> segments) {
        List<String> sixSegmentsDigits = numbers.stream().filter(n -> n.length() == 6).toList();
        List<Character> allSegments = numbers.stream().filter(n -> n.length() == 7).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        List<Character> difference = new ArrayList<>();
        List<Character> first = sixSegmentsDigits.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> second = sixSegmentsDigits.get(1).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> third = sixSegmentsDigits.get(2).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        difference.add(ListUtils.subtract(allSegments, first).get(0));
        difference.add(ListUtils.subtract(allSegments, second).get(0));
        difference.add(ListUtils.subtract(allSegments, third).get(0));

        return ListUtils.subtract(difference.stream().map(Object::toString).toList(), segments).get(0);
    }

    static String determineSegmentG(List<String> numbers, List<String> segments) {
        List<Character> allSegments = numbers.stream().filter(n -> n.length() == 7).findFirst().get().chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        return ListUtils.subtract(allSegments.stream().map(Object::toString).toList(), segments).get(0);
    }
}
