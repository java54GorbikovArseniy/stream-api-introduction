package telran.streams.students;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class College implements Iterable<Student> {
    Student[] students;

    public College(Student[] students) {
        this.students = Arrays.copyOf(students, students.length);
    }

    @Override
    public Iterator<Student> iterator() {
        return new CollegeIterator();
    }

    private class CollegeIterator implements Iterator<Student> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < students.length;
        }

        @Override
        public Student next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return students[index++];
        }
    }
}
