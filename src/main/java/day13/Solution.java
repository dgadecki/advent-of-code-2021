package day13;

import common.InputReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Advent of code - day 13
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static long firstPart() {
        List<String> inputAsString = new InputReader().readInput("input_day13");
        List<Dot> dots = new ArrayList<>();
        List<Pair<String, Long>> commands = new ArrayList<>();
        boolean wasEmptyLine = false;
        for (String line : inputAsString) {
            if (StringUtils.EMPTY.equals(line)) {
                wasEmptyLine = true;
                continue;
            }
            if (wasEmptyLine) {
                String[] command = line.split(" ")[2].split("=");
                commands.add(Pair.of(command[0], Long.valueOf(command[1])));
            } else {
                String[] coordinates = line.split(",");
                Dot dot = new Dot(Long.parseLong(coordinates[0]), Long.parseLong(coordinates[1]));
                dots.add(dot);
            }
        }

        Pair<String, Long> command = commands.get(0);
        if (command.getKey().equals("x")) {
            for (Dot dot : dots) {
                if (dot.x > command.getValue()) {
                    dot.x -= (dot.x - command.getValue()) * 2;
                }
            }
        } else {
            for (Dot dot : dots) {
                if (dot.y > command.getValue()) {
                    dot.y -= (dot.y - command.getValue()) * 2;
                }
            }
        }
        return new HashSet<>(dots).size();
    }

    public static long secondPart() {
        List<String> inputAsString = new InputReader().readInput("input_day13");
        Set<Dot> dots = new HashSet<>();
        List<Pair<String, Long>> commands = new ArrayList<>();
        boolean wasEmptyLine = false;
        for (String line : inputAsString) {
            if (StringUtils.EMPTY.equals(line)) {
                wasEmptyLine = true;
                continue;
            }
            if (wasEmptyLine) {
                String[] command = line.split(" ")[2].split("=");
                commands.add(Pair.of(command[0], Long.valueOf(command[1])));
            } else {
                String[] coordinates = line.split(",");
                Dot dot = new Dot(Long.parseLong(coordinates[0]), Long.parseLong(coordinates[1]));
                dots.add(dot);
            }
        }

        for (Pair<String, Long> command : commands) {
            if (command.getKey().equals("x")) {
                for (Dot dot : dots) {
                    if (dot.x > command.getValue()) {
                        dot.x -= (dot.x - command.getValue()) * 2;
                    }
                }
            } else {
                for (Dot dot : dots) {
                    if (dot.y > command.getValue()) {
                        dot.y -= (dot.y - command.getValue()) * 2;
                    }
                }
            }
        }
        dots.forEach(dot -> System.out.println("("+ dot.x + "," + (dot.y) + ")"));
        return 0L;
    }

    static class Dot {
        long x;
        long y;

        public Dot(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dot dot = (Dot) o;
            return x == dot.x && y == dot.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
