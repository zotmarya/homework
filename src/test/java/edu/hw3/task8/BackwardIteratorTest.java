package edu.hw3.task8;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BackwardIteratorTest {

    @Test
    void backwardIteratorNext_WhenPassingCollection_ReturnsLastElement() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        Integer number = 0;

        if (backwardIterator.hasNext()) {
            number = backwardIterator.next();
        }

        assertThat(number).isEqualTo(3);
    }

    @Test
    void backwardIteratorNext_WhenPassingCollectionOfOneElement_ReturnsElement() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(5));
        Integer number = 0;

        if (backwardIterator.hasNext()) {
            number = backwardIterator.next();
        }

        assertThat(number).isEqualTo(5);
    }

    @Test
    void backwardIteratorNext_WhenPassingEmptyCollection_ReturnsNull() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of());

        Integer number = backwardIterator.next();

        assertThat(number).isNull();
    }

    @Test
    void backwardIteratorNext_WhenPassingNull_ReturnsNull() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(null);

        Integer number = backwardIterator.next();

        assertThat(number).isNull();
    }
}
