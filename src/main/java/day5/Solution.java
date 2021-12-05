package day5;

import common.InputReader;

import java.util.List;

/**
 * Advent of code - day 5
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<Coordinates> inputAsString = new InputReader().readInput("input_day5").stream()
                .map(x -> x.replaceAll(" +", ""))
                .map(x -> x.replaceAll("->", ","))
                .map(x -> x.split(","))
                .map(x -> new Coordinates(Integer.parseInt(x[0]), Integer.parseInt(x[1]), Integer.parseInt(x[2]), Integer.parseInt(x[3])))
                .filter(c -> c.x1 == c.x2 || c.y1 == c.y2)
                .toList();

        int[][] board = new int[1000][1000];

        for (Coordinates coordinate : inputAsString) {
            if (coordinate.x1 == coordinate.x2) {
                for (int i = Math.min(coordinate.y1, coordinate.y2); i <= Math.max(coordinate.y1, coordinate.y2); i++) {
                    board[coordinate.x1][i]++;
                }
            }
            if (coordinate.y1 == coordinate.y2) {
                for (int i = Math.min(coordinate.x1, coordinate.x2); i <= Math.max(coordinate.x1, coordinate.x2); i++) {
                    board[i][coordinate.y1]++;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (board[i][j] >= 2) {
                    result++;
                }
            }
        }

        return result;
    }

    public static int secondPart() {
        List<Coordinates> verticalAndHorizontal = new InputReader().readInput("input_day5").stream()
                .map(x -> x.replaceAll(" +", ""))
                .map(x -> x.replaceAll("->", ","))
                .map(x -> x.split(","))
                .map(x -> new Coordinates(Integer.parseInt(x[0]), Integer.parseInt(x[1]), Integer.parseInt(x[2]), Integer.parseInt(x[3])))
                .filter(c -> c.x1 == c.x2 || c.y1 == c.y2)
                .toList();

        List<Coordinates> diagonal = new InputReader().readInput("input_day5").stream()
                .map(x -> x.replaceAll(" +", ""))
                .map(x -> x.replaceAll("->", ","))
                .map(x -> x.split(","))
                .map(x -> new Coordinates(Integer.parseInt(x[0]), Integer.parseInt(x[1]), Integer.parseInt(x[2]), Integer.parseInt(x[3])))
                .filter(c -> !(c.x1 == c.x2 || c.y1 == c.y2))
                .toList();

        int[][] board = new int[1000][1000];

        for (Coordinates coordinate : verticalAndHorizontal) {
            if (coordinate.x1 == coordinate.x2) {
                for (int i = Math.min(coordinate.y1, coordinate.y2); i <= Math.max(coordinate.y1, coordinate.y2); i++) {
                    board[coordinate.x1][i]++;
                }
            }
            if (coordinate.y1 == coordinate.y2) {
                for (int i = Math.min(coordinate.x1, coordinate.x2); i <= Math.max(coordinate.x1, coordinate.x2); i++) {
                    board[i][coordinate.y1]++;
                }
            }
        }

        for (Coordinates c : diagonal) {
            if (c.x1 < c.x2) {
                for (int i = 0; i <= c.x2 - c.x1; i++) {
                    if (c.y1 > c.y2) {
                        board[c.x1 + i][c.y1 - i]++;
                    } else {
                        board[c.x1 + i][c.y1 + i]++;
                    }
                }
            } else if (c.x1 > c.x2) {
                for (int i = 0; i <= c.x1 - c.x2; i++) {
                    if (c.y1 > c.y2) {
                        board[c.x1 - i][c.y1 - i]++;
                    } else {
                        board[c.x1 - i][c.y1 + i]++;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (board[i][j] >= 2) {
                    result++;
                }
            }
        }
        return result;
    }

    private static class Coordinates {
        public int x1;
        public int y1;
        public int x2;
        public int y2;

        public Coordinates(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
