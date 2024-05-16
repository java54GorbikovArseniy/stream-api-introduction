package telran.streams;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class StreamsIntroductionMethods {
    static public int sumIf(int ar[], Predicate<Integer> predicate){
        return Arrays.stream(ar).filter(n -> predicate.test(n)).sum();
    }
    static public int sumDistinct(int ar[]){
        return Arrays.stream(ar).distinct().sum();
    }

    static public int maxIf(int[] ar, Predicate<Integer> predicate){
        return Arrays.stream(ar)
                .filter(n -> predicate.test(n))
                .max()
                .orElseThrow(() -> new NoSuchElementException("emptyStream"));
    }

    static public int[] sortDistinct(int ar[]){
        return Arrays.stream(ar).distinct().sorted().toArray();
    }

    static public void forEachIf(int ar[], Predicate<Integer> ifPredicate, Consumer<Integer> forEachMethod){
        Arrays.stream(ar)
                .filter(n -> ifPredicate.test(n))
                .mapToObj(n -> n)
                .forEach(n -> forEachMethod.accept(n));
    }

    static public int[] getRandomArray(int fromInclusive, int toExclusive, int nNumbers){
        return new Random().ints(nNumbers, fromInclusive, toExclusive).toArray();
    }

    static public void displayShuffle(int ar[]) {
        Random random = new Random();
        IntStream.range(0, ar.length)
                .forEach(i -> {
                    int j = random.nextInt(ar.length - i) + i;
                    int temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;
                });
        Arrays.stream(ar).forEach((el) -> System.out.print(el + " "));
    }
}
