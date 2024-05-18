package telran.streams.tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static telran.streams.StreamsIntroductionMethods.*;

public class StreamsIntroductionTests {

    @Test
    void sumIfTest() {
        int[] array = {1, 2, 3, 4, 5, 6};
        assertEquals(12, sumIf(array, n -> n % 2 == 0));
    }

    @Test
    void sumDistinctTest() {
        int[] array = {1, 2, 10, 1, 1};
        assertEquals(13, sumDistinct(array));
    }

    @Test
    void maxIfTest() {
        int[] array = {1, 7, -3, 23, 13, 101};
        assertEquals(101, maxIf(array, n -> n % 2 != 0));
        assertThrowsExactly(NoSuchElementException.class, () -> maxIf(array, n -> n % 2 == 0));
    }

    @Test
    void sortDistinctTest() {
        int[] array = {1, 10, 1, 1, 2};
        int[] expected = {1, 2, 10};
        assertArrayEquals(expected, sortDistinct(array));
    }

    @Test
    void forEachIfTest() {
        int[] array = {1, 10, 1, 1, 2};
        forEachIf(array, n -> n % 2 != 0, n -> System.out.print(n + " "));
    }

    @Test
    void getRandomArrayTest() {
        int[] ar = getRandomArray(10, 12, 1000);
        assertEquals(1000, ar.length);
        assertTrue(Arrays.stream(ar).allMatch(n -> n >= 10 && n < 12));
        assertEquals(21, sumDistinct(ar));
    }

    @Test
    void displayShuffleTest() {
        int[] testArray = {10, 20, 30, 40};
        displayShuffle(testArray);
    }
}
