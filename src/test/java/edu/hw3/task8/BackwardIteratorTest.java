package edu.hw3.task8;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BackwardIteratorTest {
    @Test
    void backwardIteratorNext_WhenPassingCollection_ReturnsElementsBackwards() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        List<Integer> nums = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            nums.add(backwardIterator.next());
        }

        assertThat(nums).containsExactly(3,2,1);
    }

    @Test
    void backwardIteratorNext_WhenPassingNull_ReturnsNull() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(null);
        List<Integer> nums = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            nums.add(backwardIterator.next());
        }

        assertThat(nums).isEmpty();
    }
}
