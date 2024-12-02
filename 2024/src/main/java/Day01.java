import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day01 {
    public static void main(String[] args) throws Exception {
        Pair<List<Integer>, List<Integer>> lists = getListsFromInput("day01.txt");
        int difference = calculateTotalDifference(lists.getLeft(), lists.getRight());
        System.out.println("Day01 part 1 solution: " +  difference);
        int similarityScore = calculateSimilarityScore(lists.getLeft(), lists.getRight());
        System.out.println("Day01 part 2 solution: " +  similarityScore);
    }

    /**
     * Input file is two numbers per line separated by three spaces forming two columns of numbers.
     * Returns a pair of lists with the values of each column.
     */
    private static Pair<List<Integer>, List<Integer>> getListsFromInput(String filename) throws Exception {
        Path path = Paths.get("./src/main/resources/input/"+filename);
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        Files.lines(path)
                .map(line -> line.split(" {3}"))
                .forEach(splitLine -> {
                    listA.add(Integer.valueOf(splitLine[0]));
                    listB.add(Integer.valueOf(splitLine[1]));
                });
        return Pair.of(listA, listB);
    }

    /**
     * Calculate the total difference in values between two lists arranged by size
     */
    public static int calculateTotalDifference(List<Integer> leftList, List<Integer> rightList) throws Exception {
        if (leftList.size() != rightList.size()) {
            throw new Exception("Size of lists be equal");
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        int totalDifference = 0;
        for (int i = 0; i < leftList.size(); i++) {
            totalDifference += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return totalDifference;
    }

    /**
     * Calculates the sum of each id multiplied by its number of occurrences in the left list multiplied by its
     * number of occurrences in right list
     */
    public static int calculateSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, int[]> idCounts = new HashMap<>();

        // Count occurrences of ids in left list
        leftList.forEach(id -> {
            idCounts.putIfAbsent(id, new int[]{0, 0});
            idCounts.get(id)[0] += 1;
        });

        // Count occurrences in right list
        rightList.forEach(id -> {
            if (idCounts.containsKey(id)) {
                idCounts.get(id)[1] += 1;
            }
        });

        // id number * occurrences in left list * occurrences in right list
        return idCounts.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue()[0]
                * entry.getValue()[1]).sum();
    }
}
