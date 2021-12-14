package day12;

import common.InputReader;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Advent of code - day 12
 */
public class Solution {

    private static final String START_CAVE = "start";
    private static final String END_CAVE = "end";

    public static void main(String[] args) {
        System.out.println("First result: " + firstPart());
        System.out.println("Second result: " + secondPart());
    }

    public static long firstPart() {
        Map<String, List<String>> cavesGraph = buildCavesGraph(
                new InputReader().readInput("input_day12")
        );
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>(Collections.singletonList(START_CAVE));
        calculatePathForPartOne(path, cavesGraph, paths);

        return paths.size();
    }

    public static long secondPart() {
        Map<String, List<String>> cavesGraph = buildCavesGraph(
                new InputReader().readInput("input_day12")
        );
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>(Collections.singletonList(START_CAVE));
        calculatePathForPartTwo(path, cavesGraph, paths);

        return paths.size();
    }

    static Map<String, List<String>> buildCavesGraph(List<String> input) {
        Map<String, List<String>> connections = new HashMap<>();
        for (String path : input) {
            String[] pathCaves = path.split("-");
            connections.computeIfAbsent(pathCaves[0], v -> new ArrayList<>()).add(pathCaves[1]);
            connections.computeIfAbsent(pathCaves[1], v -> new ArrayList<>()).add(pathCaves[0]);
        }
        return connections;
    }

    static void calculatePathForPartOne(List<String> currentPath, Map<String, List<String>> cavesGraph, List<List<String>> paths) {
        String currentCave = currentPath.get(currentPath.size() - 1);
        List<String> currentCaveConnections = cavesGraph.get(currentCave);
        if (currentCave.equals(END_CAVE)) {
            paths.add(currentPath);
        } else {
            for (String nextCave : currentCaveConnections) {
                if (isFurtherPathCalculationPossible(nextCave, currentPath)) {
                    List<String> newCurrentPath = new ArrayList<>(currentPath);
                    newCurrentPath.add(nextCave);
                    calculatePathForPartOne(newCurrentPath, cavesGraph, paths);
                }
            }
        }
    }

    static boolean isFurtherPathCalculationPossible(String connection, List<String> currentPath) {
        return !connection.equals(START_CAVE) && !(StringUtils.isAllLowerCase(connection) && currentPath.contains(connection));
    }

    static void calculatePathForPartTwo(List<String> currentPath, Map<String, List<String>> cavesGraph, List<List<String>> paths) {
        String currentCave = currentPath.get(currentPath.size() - 1);
        List<String> currentCaveConnections = cavesGraph.get(currentCave);
        if (currentCave.equals(END_CAVE)) {
            paths.add(currentPath);
        } else {
            Map<String, Long> caveAndVisits = new HashMap<>();
            for (String cave : currentPath) {
                if (StringUtils.isAllLowerCase(cave) && !cave.equals(START_CAVE)) {
                    caveAndVisits.put(cave, caveAndVisits.getOrDefault(cave, 0L) + 1L);
                }
            }
            if (caveAndVisits.values().stream().filter(v -> v > 1L).count() > 1L) {
                return;
            }
            for (String nextCave : currentCaveConnections) {
                if (isFurtherPathCalculationPossible(nextCave, caveAndVisits)) {
                    List<String> newCurrentPath = new ArrayList<>(currentPath);
                    newCurrentPath.add(nextCave);
                    calculatePathForPartTwo(newCurrentPath, cavesGraph, paths);
                }
            }
        }
    }

    static boolean isFurtherPathCalculationPossible(String connection, Map<String, Long> caveAndVisits) {
        return !connection.equals(START_CAVE) && caveAndVisits.getOrDefault(connection, 0L) < 2L;
    }
}
