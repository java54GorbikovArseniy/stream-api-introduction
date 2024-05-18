package telran.streams.tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TwoDimensionalArrayTest {
    int[][] array = {
            {10, 5, 6},
            {20, 20, 20},
            {10, 4}
    };

    @Test
    void sortTest() {
        int[][] expected = {
                {10, 4},
                {10, 5, 6},
                {20, 20, 20}
        };
        assertArrayEquals(expected, arraysSort(array));
    }

    @Test
    void sumTest() {
        assertEquals(95, sumArrays1(array));
        assertEquals(95, sumArrays2(array));
        assertEquals(95, sumArrays3(array));
    }

    @Test
    void summaryStatisticsTest() {
        IntSummaryStatistics iss = Arrays.stream(array).flatMapToInt(a -> Arrays.stream(a)).summaryStatistics();
        assertEquals(4, iss.getMin());
        assertEquals(20, iss.getMax());
        assertEquals(95, iss.getSum());
        assertEquals(11.875, iss.getAverage());
    }

    private int sumArrays3(int[][] array) {
        return Arrays.stream(array).flatMapToInt(a -> Arrays.stream(a)).sum();
    }

    private int sumArrays2(int[][] array) {
        return Arrays.stream(array).mapToInt(a -> Arrays.stream(a).sum()).sum();
    }

    private static int sumArrays1(int[][] array) {

        return Arrays.stream(array).collect(Collectors.summingInt(a -> Arrays.stream(a).sum()));
    }

    private static int[][] arraysSort(int[][] ar) {
        return Arrays.stream(ar)
                .sorted(Comparator.comparingInt(a -> Arrays.stream(a).sum()))
                .toArray(int[][]::new);
    }
}
