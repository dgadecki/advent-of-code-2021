package day11;

import common.InputReader;

import java.util.List;

/**
 * Advent of code - day 11
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static long firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day11");
        Octopus[][] octopus = new Octopus[10][10];
        for (int i = 0; i < 10; i++) {
            Octopus[] singleLine = inputAsString.get(i).chars().map(x -> x - '0').boxed().map(Octopus::new).toArray(Octopus[]::new);
            octopus[i] = singleLine;
        }

        int numberOfSteps = 100;
        int result = 0;
        for(int n = 0; n < numberOfSteps; n++) {
            result += calculateStep(octopus);
        }
        return result;
    }

    public static long secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day11");
        Octopus[][] octopus = new Octopus[10][10];
        for (int i = 0; i < 10; i++) {
            Octopus[] singleLine = inputAsString.get(i).chars().map(x -> x - '0').boxed().map(Octopus::new).toArray(Octopus[]::new);
            octopus[i] = singleLine;
        }

        int result = 1;
        while (!isCommonFlash(octopus)) {
            result++;
        }
        return result;
    }

    static boolean isCommonFlash(Octopus[][] octopus) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                calculateSingleOctopus(i, j, octopus);
            }
        }
        boolean isCommonFlash = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (octopus[i][j].flash) {
                    octopus[i][j].flash = false;
                    octopus[i][j].energy = 0;
                } else {
                    isCommonFlash = false;
                }
            }
        }
        return isCommonFlash;
    }

    static int calculateStep(Octopus[][] octopus) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
               calculateSingleOctopus(i, j, octopus);
            }
        }
        int stepResult = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (octopus[i][j].flash) {
                    octopus[i][j].flash = false;
                    octopus[i][j].energy = 0;
                    stepResult += 1;
                }
            }
        }
        return stepResult;
    }

    static void calculateSingleOctopus(int i, int j, Octopus[][] octopus) {
        if (i < 0 || j < 0 || i > 9 || j > 9) {
            return;
        }
        Octopus oct = octopus[i][j];
        oct.energy += 1;
        if (oct.energy > 9 && !oct.flash) {
            oct.flash = true;
            calculateSingleOctopus(i + 1, j - 1, octopus);
            calculateSingleOctopus(i + 1, j, octopus);
            calculateSingleOctopus(i + 1, j + 1, octopus);
            calculateSingleOctopus(i, j - 1, octopus);
            calculateSingleOctopus(i, j + 1, octopus);
            calculateSingleOctopus(i - 1, j - 1, octopus);
            calculateSingleOctopus(i - 1, j, octopus);
            calculateSingleOctopus(i - 1, j + 1, octopus);
        }
    }

    static class Octopus {
        boolean flash;
        int energy;

        public Octopus(int energy) {
            this.energy = energy;
        }
    }
}
