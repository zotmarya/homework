package edu.hw10.task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomObjectGeneratorTest {

    @Test
    void nextObject_WhenGivenClass_CreatesNewRandomObject() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        MyRecord newObject = rog.nextObject(MyRecord.class);

        assertThat(newObject).isNotNull();
    }

    @Test
    void nextObject_WhenGivenClassAndFabricMethodName_CreatesNewRandomObject() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        MyClass newObject = rog.nextObject(MyClass.class, "create");

        assertThat(newObject).isNotNull();
    }

    @Test
    void nextObject_WhenGivenClassWithNotNullAnnotation_CreatesNewRandomObjectWithNotNullField() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        MyClass newObject = rog.nextObject(MyClass.class, "create");

        assertThat(newObject.getName()).isNotNull();
    }

    @Test
    void nextObject_WhenGivenClassWithMinMaxAnnotatedField_CreatesNewRandomObjectWithFieldValueInRange() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        MyRecord newObject = rog.nextObject(MyRecord.class);

        assertThat(newObject.age()).isBetween(0, 120);
    }
}
