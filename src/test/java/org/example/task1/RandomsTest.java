package org.example.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RandomsTest {

    @Test
    void getRandom_expectedBehavior() {
        int min = 10;
        int max = 20;
        Randoms randoms = new Randoms(min, max);
        for (int i = 0; i < 10_000; i++) {
            int random = randoms.getRandom();
            Assertions.assertTrue(random >= min && random <= max);
        }
    }

    @Test
    void getRandom_withListTest_expectedBehavior() {
        int min = 10;
        int max = 20;
        int listSize = 10_000;
        Randoms randoms = new Randoms(min, max);
        List<Integer> integers = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            integers.add(randoms.getRandom());
        }

        Assertions.assertEquals(min, Collections.min(integers));
        Assertions.assertEquals(max, Collections.max(integers));
    }
}