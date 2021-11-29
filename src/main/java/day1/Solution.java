package day1;

import common.InputReader;

import java.util.List;

/**
 * Advent of code - day 1
 */
public class Solution {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        List<String> input = inputReader.readInput("input_day1");
        System.out.println(input.stream().toList());
    }
}
