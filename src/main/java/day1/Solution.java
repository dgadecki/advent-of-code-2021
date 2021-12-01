package day1;

import common.InputReader;

import java.util.List;

/**
 * Advent of code - day 1
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result:" + firstPart());
        System.out.println("Second result:" + secondPart());
    }

    public static int firstPart() {
        List<Long> input = new InputReader().readInput("input_day1")
                .stream()
                .map(Long::valueOf)
                .toList();

        int result = 0;
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i) < input.get(i + 1)) {
                result++;
            }
        }
        return result;
    }

    public static int secondPart() {
        List<Long> input = new InputReader().readInput("input_day1")
                .stream()
                .map(Long::valueOf)
                .toList();

        int result = 0;
        for (int i = 2; i < input.size() - 1; i++) {
            long firstWindowValue = input.get(i - 2) + input.get(i - 1) + input.get(i);
            long secondWindowValue = input.get(i - 1) + input.get(i) + input.get(i + 1);
            if (firstWindowValue < secondWindowValue) {
                result++;
            }
        }
        return result;
    }
}
