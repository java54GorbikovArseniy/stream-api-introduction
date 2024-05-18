package telran.streams.students;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CollegeTest {

    private static final String NAME1 = "Bryan";
    private static final String NAME2 = "Tom";
    private static final String NAME3 = "Bob";
    private static final int HOURS1 = 100;
    private static final int HOURS2 = 100;
    private static final int HOURS3 = 150;
    private static final int[] MARKS1 = {60, 70, 80};
    private static final int[] MARKS2 = {80, 60, 60};
    Student st1 = new Student(NAME1, HOURS1, MARKS1);
    Student st2 = new Student(NAME2, HOURS2, MARKS2);
    Student st3 = new Student(NAME3, HOURS3, MARKS2);

    College college = new College(new Student[]{st1, st2, st3});

    @Test
    void sortTest() {
        Student[] expected = {st1, st3, st2};
        assertArrayEquals(expected, sortStudents(college));
    }

    @Test
    void summaryStatisticsHoursTest() {
        IntSummaryStatistics iss = getHoursStatistics(college);
        assertEquals(100, iss.getMin());
        assertEquals(150, iss.getMax());
        assertEquals(350, iss.getSum());
    }

    @Test
    void summaryStatisticsMarks() {
        IntSummaryStatistics iss = getMarksStatistics(college);
        assertEquals(60, iss.getMin());
        assertEquals(80, iss.getMax());
    }

    private static IntSummaryStatistics getMarksStatistics(College coll) {
        return Arrays.stream(coll.students)
                .flatMapToInt(s -> Arrays.stream(s.marks()))
                .summaryStatistics();
    }

    static private IntSummaryStatistics getHoursStatistics(College col) {
        return Arrays.stream(col.students)
                .mapToInt(Student::hours)
                .summaryStatistics();
    }

    private static Student[] sortStudents(College col) {
        return StreamSupport.stream(col.spliterator(), false)
                .sorted(Comparator.comparingDouble((Student s) -> Arrays.stream(s.marks()).average().orElse(0.0))
                        .thenComparingInt(Student::hours).reversed())
                .toArray(Student[]::new);
    }
}