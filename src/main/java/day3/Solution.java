package day3;

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
        List<String> inputAsString = new InputReader().readInput("input_day3");
        StringBuilder gammaValues = new StringBuilder();
        StringBuilder epsilonValues = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int countBit0 = 0;
            for (String s : inputAsString) {
                if (s.charAt(i) == '0') {
                    countBit0++;
                }
            }
            if (countBit0 > (inputAsString.size() - countBit0)) {
                gammaValues.append("0");
                epsilonValues.append("1");
            } else {
                gammaValues.append("1");
                epsilonValues.append("0");
            }
        }
        return Integer.parseInt(gammaValues.toString(),2) * Integer.parseInt(epsilonValues.toString(),2);
    }

    public static int secondPart() {
        List<String> inputForOxygenCalculation = new InputReader().readInput("input_day3");
        List<String> inputForCO2Calculation = new InputReader().readInput("input_day3");
        String oxygen = null;
        String co2 = null;

        // Calculate oxygen
        for (int i = 0; i < 12; i++) {
            int countBit0 = 0;
            int countBit1 = 0;
            for (int j = 0; j < inputForOxygenCalculation.size(); j++) {
                int bitValue = Integer.parseInt(inputForOxygenCalculation.get(j).substring(i, i + 1));
                if (bitValue == 0) {
                    countBit0++;
                } else {
                    countBit1++;
                }
            }
            int finalIndex = i;
            if (countBit0 > countBit1) {
                inputForOxygenCalculation = inputForOxygenCalculation.stream()
                        .filter(x -> x.charAt(finalIndex) == '0')
                        .toList();
            } else {
                inputForOxygenCalculation = inputForOxygenCalculation.stream()
                        .filter(x -> x.charAt(finalIndex) == '1')
                        .toList();
            }
            if (inputForOxygenCalculation.size() == 1) {
               oxygen = inputForOxygenCalculation.get(0);
            }
        }

        // Calculate CO2
        for (int i = 0; i < 12; i++) {
            int countBit0 = 0;
            int countBit1 = 0;
            for (int j = 0; j < inputForCO2Calculation.size(); j++) {
                int bitValue = Integer.parseInt(inputForCO2Calculation.get(j).substring(i, i + 1));
                if (bitValue == 0) {
                    countBit0++;
                } else {
                    countBit1++;
                }
            }
            int finalIndex = i;
            if (countBit0 <= countBit1) {
                inputForCO2Calculation = inputForCO2Calculation.stream()
                        .filter(x -> x.charAt(finalIndex) == '0')
                        .toList();
            } else {
                inputForCO2Calculation = inputForCO2Calculation.stream()
                        .filter(x -> x.charAt(finalIndex) == '1')
                        .toList();
            }
            if (inputForCO2Calculation.size() == 1) {
                co2 = inputForCO2Calculation.get(0);
            }
        }
        return Integer.parseInt(oxygen,2) * Integer.parseInt(co2, 2);
    }
}
