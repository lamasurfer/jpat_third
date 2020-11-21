package org.example.task1;

import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {
    private final Random random;
    private final int min;
    private final int max;

    public Randoms(int min, int max) {
        this.min = min;
        this.max = max;
        this.random = new Random();
    }

    public int getRandom() {
        return min + random.nextInt(max - min + 1);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return getRandom();
            }
        };
    }
}