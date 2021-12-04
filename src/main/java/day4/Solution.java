package day4;

import common.InputReader;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Advent of code - day 4
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static int firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day4").stream().filter(StringUtils::isNotBlank).toList();
        List<Integer> values = Arrays.stream(inputAsString.get(0).split(",")).map(Integer::parseInt).toList();
        List<Integer[][]> boards = new ArrayList<>();
        for (int i = 1; i < inputAsString.size(); i = i + 5) {
            Integer[][] singleBoard = {
                    Arrays.stream(inputAsString.get(i).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+1).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+2).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+3).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+4).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
            };
            boards.add(singleBoard);
        }
        for (Integer value : values) {
            for (Integer[][] board : boards) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j].equals(value)) {
                            board[i][j] = -1;
                        }
                    }
                }
            }
            for (Integer[][] boardToCheck : boards) {
                for (int i = 0; i < 5; i++) {
                    if (boardToCheck[i][0] == -1 && boardToCheck[i][1] == -1 && boardToCheck[i][2] == -1 && boardToCheck[i][3] == -1 && boardToCheck[i][4] == -1) {
                        int otherBoardValues = 0;
                        for (int o = 0; o < 5; o++) {
                            for (int p = 0; p < 5; p++) {
                                if (boardToCheck[o][p] != -1) {
                                    otherBoardValues += boardToCheck[o][p];
                                }
                            }
                        }
                        return otherBoardValues * value;
                    } else if (boardToCheck[0][i] == -1 && boardToCheck[1][i] == -1 && boardToCheck[2][i] == -1 && boardToCheck[3][i] == -1 && boardToCheck[4][i] == -1) {
                        int otherBoardValues = 0;
                        for (int o = 0; o < 5; o++) {
                            for (int p = 0; p < 5; p++) {
                                if (boardToCheck[o][p] != -1) {
                                    otherBoardValues += boardToCheck[o][p];
                                }
                            }
                        }
                        return otherBoardValues * value;
                    }
                }
            }
        }
        return 0;
    }

    public static int secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day4").stream().filter(StringUtils::isNotBlank).toList();
        List<Integer> values = Arrays.stream(inputAsString.get(0).split(",")).map(Integer::parseInt).toList();
        List<Integer[][]> boards = new ArrayList<>();
        List<Integer[][]> wonBoards = new ArrayList<>();
        for (int i = 1; i < inputAsString.size(); i = i + 5) {
            Integer[][] singleBoard = {
                    Arrays.stream(inputAsString.get(i).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+1).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+2).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+3).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
                    Arrays.stream(inputAsString.get(i+4).split(" ")).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList().toArray(new Integer[0]),
            };
            boards.add(singleBoard);
        }
        for (Integer value : values) {
            for (Integer[][] board : boards) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j].equals(value)) {
                            board[i][j] = -1;
                        }
                    }
                }
            }
            for (Integer[][] boardToCheck : boards) {
                for (int i = 0; i < 5; i++) {
                    // Line check
                    if (boardToCheck[i][0] == -1 && boardToCheck[i][1] == -1 && boardToCheck[i][2] == -1 && boardToCheck[i][3] == -1 && boardToCheck[i][4] == -1) {
                        if (!wonBoards.contains(boardToCheck)) {
                            wonBoards.add(boardToCheck);
                        }

                    } else if (boardToCheck[0][i] == -1 && boardToCheck[1][i] == -1 && boardToCheck[2][i] == -1 && boardToCheck[3][i] == -1 && boardToCheck[4][i] == -1) {
                        if (!wonBoards.contains(boardToCheck)) {
                            wonBoards.add(boardToCheck);
                        }
                    }
                }
            }
            if (boards.size() == wonBoards.size()) {
                Integer[][] lastWinBoard = wonBoards.get(wonBoards.size() - 1);
                int otherBoardValues = 0;
                for (int o = 0; o < 5; o++) {
                    for (int p = 0; p < 5; p++) {
                        if (lastWinBoard[o][p] != -1) {
                            otherBoardValues += lastWinBoard[o][p];
                        }
                    }
                }
                return otherBoardValues * value;
            }
        }
        return 0;
    }
}
