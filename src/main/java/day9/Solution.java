package day9;

import common.InputReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Advent of code - day 9
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day9");
        int maxX = inputAsString.size();
        int maxY = inputAsString.get(0).toCharArray().length;

        int[][] input = new int[maxX][maxY];

        for (int i = 0; i < inputAsString.size(); i++) {
            int[] singleLine = inputAsString.get(i).chars().map(x -> x - '0').toArray();
            input[i] = singleLine;
        }

        int result = 0;
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int current = input[x][y];
                int left = x > 0 ? input[x - 1][y] : Integer.MAX_VALUE;
                int right = x < maxX - 1 ? input[x + 1][y] : Integer.MAX_VALUE;
                int top = y > 0 ? input[x][y - 1] : Integer.MAX_VALUE;
                int bottom = y < maxY - 1 ? input[x][y + 1] : Integer.MAX_VALUE;

                if (current < left && current < right && current < top && current < bottom) {
                    result += current + 1;
                }
            }
        }
        return result;
    }

    public static int secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day9");
        int maxX = inputAsString.size();
        int maxY = inputAsString.get(0).toCharArray().length;

        int[][] input = new int[maxX][maxY];

        for (int i = 0; i < inputAsString.size(); i++) {
            int[] singleLine = inputAsString.get(i).chars().map(x -> x - '0').toArray();
            input[i] = singleLine;
        }
        List<Integer> basinSizes = new ArrayList<>();
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int basinSize = calculateSingleBasin(input, x, y, maxX, maxY);
                basinSizes.add(basinSize);
            }
        }

        return basinSizes.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (x, y) -> x * y);
    }

    static Integer calculateSingleBasin(int[][] input, int x, int y, int maxX, int maxY) {
        if (x < 0 || y < 0 || x >= maxX || y >= maxY || input[x][y] >= 9) {
            return 0;
        }
        input[x][y] = Integer.MAX_VALUE;
        return 1 + calculateSingleBasin(input, x - 1, y, maxX, maxY) + calculateSingleBasin(input, x + 1, y, maxX, maxY)
                + calculateSingleBasin(input, x, y - 1, maxX, maxY) + calculateSingleBasin(input, x, y + 1, maxX, maxY);
    }
}
