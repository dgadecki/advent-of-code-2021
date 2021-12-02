package day2;

import common.InputReader;

import java.util.List;

/**
 * Advent of code - day 2
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day2");
        int horizontal = 0;
        int depth = 0;

        for (String input : inputAsString) {
            String course = input.split(" ")[0];
            int courseValue = Integer.parseInt(input.split(" ")[1]);
            if ("forward".equals(course)) {
                horizontal += courseValue;
            }
            if ("down".equals(course)) {
                depth += courseValue;
            }
            if ("up".equals(course)) {
                depth -= courseValue;
            }
        }
        return horizontal * depth;
    }

    public static int secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day2");
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (String input : inputAsString) {
            String course = input.split(" ")[0];
            int courseValue = Integer.parseInt(input.split(" ")[1]);
            if ("forward".equals(course)) {
                horizontal += courseValue;
                depth += (aim * courseValue);
            }
            if ("down".equals(course)) {
                aim += courseValue;
            }
            if ("up".equals(course)) {
                aim -= courseValue;
            }
        }
        return horizontal * depth;
    }
}
