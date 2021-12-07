package day7;

import common.InputReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Advent of code - day 7
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<Integer> positions = Arrays.stream(new InputReader().readInput("input_day7").get(0).split(",")).map(Integer::parseInt).toList();
        int maxPosition = Collections.max(positions);
        int minPosition = Collections.min(positions);
        int best = Integer.MAX_VALUE;

        for (int i = minPosition; i < maxPosition; i++) {
            int fuel = 0;
            for(Integer position : positions) {
                fuel += Math.abs(position - i);
            }
            if (best > fuel) {
                best = fuel;
            }
        }
        return best;
    }

    public static int secondPart() {
        List<Integer> positions = Arrays.stream(new InputReader().readInput("input_day7").get(0).split(",")).map(Integer::parseInt).toList();
        int maxPosition = Collections.max(positions);
        int minPosition = Collections.min(positions);
        int best = Integer.MAX_VALUE;

        for (int i = minPosition; i < maxPosition; i++) {
            int fuel = 0;
            for(Integer position : positions) {
                int abs = Math.abs(position - i);
                // Suma ciągu arytmetycznego: 1, 2, 3, ..., abs = ((1 + a_n) * n) / 2)
                fuel += ((1 + abs) * abs) / 2;
            }
            if (best > fuel) {
                best = fuel;
            }
        }
        return best;
    }
}
