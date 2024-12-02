import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {

    private List<Integer> leftList;

    private List<Integer> rightList;

    @Before
    public void setup() {
        leftList = Arrays.asList(3, 4, 2, 1, 3, 3);
        rightList = Arrays.asList(4, 3, 5, 3, 9, 3);
    }

    @Test
    public void calculateTotalDifferenceTest() throws Exception {
        int calculatedTotalDifference = Day01.calculateTotalDifference(leftList, rightList);
        assertThat(calculatedTotalDifference)
                .as("Should equal the sum of the difference in values of elements in same position of the " +
                        "two lists when arranged by size")
                .isEqualTo(11);
    }

    @Test
    public void calculateSimilarityScoreTest() {
        int calculatedSimilarityScore = Day01.calculateSimilarityScore(leftList, rightList);
        assertThat(calculatedSimilarityScore)
                .as("Should equal the sum of each number multiplied by its number of occurrences in " +
                        "the left list multiplied by its number of occurrences in right list.")
                .isEqualTo(31);
    }
}
