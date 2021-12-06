package day6;

import common.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Advent of code - day 6
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day6");
        List<Integer> values = new ArrayList<>(
                Arrays.stream(inputAsString.get(0).split(",")).map(Integer::parseInt).toList()
        );

        for (int i = 0; i < 80; i++) {
            List<Integer> modifiedValues = new ArrayList<>();
            for (Integer value : values) {
                if (value == 0) {
                    modifiedValues.add(6);
                    modifiedValues.add(8);
                } else {
                    modifiedValues.add(value - 1);
                }
            }
            values.clear();
            values.addAll(modifiedValues);
            modifiedValues.clear();
        }
        return values.size();
    }

    public static long secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day6");
        List<Integer> input = Arrays.stream(inputAsString.get(0).split(",")).map(Integer::parseInt).toList();

        long[] values = new long[9];
        for (Integer i : input) {
            values[i]++;
        }
        for (int i = 0; i < 256; i++) {
           long newValues = values[0];
           for (int age = 0; age < 8; age++) {
               values[age] = values[age + 1];
           }
            values[8] = newValues;
            values[6] += newValues;
        }
        return Arrays.stream(values).sum();
    }
}
