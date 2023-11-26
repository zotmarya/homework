package edu.hw7.task3;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService();

        personService.add(new Person(1, "Albert", "Keesey Street", "222"));
        personService.add(new Person(2, "Irina", "Hazel Grove", "111"));
        personService.add(new Person(3, "Felicia", "Abbey Mews", "333"));
        personService.add(new Person(4, "David", "Hazel Grove", "444"));
        personService.add(new Person(5, "Emma", "Abbey Mews", "999"));
        personService.add(new Person(6, "David", "Udall Street", "777"));
        personService.add(new Person(7, "Emma", "Angel Walk", "888"));
        personService.add(new Person(8, "Alex", "Belgrove Street", "678"));
    }

    private static Stream<Arguments> nameAndPeople() {
        return Stream.of(
            arguments("Irina", List.of(
                new Person(2, "Irina", "Hazel Grove", "111")
            )),
            arguments("Emma", List.of(
                new Person(7, "Emma", "Angel Walk", "888"),
                new Person(5, "Emma", "Abbey Mews", "999")
            )),
            arguments("David", List.of(
                new Person(4, "David", "Hazel Grove", "444"),
                new Person(6, "David", "Udall Street", "777")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("nameAndPeople")
    void findByName_WhenGivenName_ReturnsListOfPersons(String name, List<Person> expectedPeople) {
        List<Person> resultPeople = personService.findByName(name);

        assertThat(resultPeople).containsExactlyInAnyOrderElementsOf(expectedPeople);
    }

    @Test
    void findByName_WhenGivenInvalidName_ReturnsNull() {
        List<Person> resultPeople = personService.findByName("Hermione");

        assertThat(resultPeople).isNull();
    }

    private static Stream<Arguments> addressAndPeople() {
        return Stream.of(
            arguments("Angel Walk", List.of(
                new Person(7, "Emma", "Angel Walk", "888")
            )),
            arguments("Udall Street", List.of(
                new Person(6, "David", "Udall Street", "777")
            )),
            arguments("Abbey Mews", List.of(
                new Person(5, "Emma", "Abbey Mews", "999"),
                new Person(3, "Felicia", "Abbey Mews", "333")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("addressAndPeople")
    void findByAddress_WhenGivenAddress_ReturnsListOfPersons(String address, List<Person> expectedPeople) {
        List<Person> resultPeople = personService.findByAddress(address);

        assertThat(resultPeople).containsExactlyInAnyOrderElementsOf(expectedPeople);
    }

    @Test
    void findByAddress_WhenGivenInvalidAddress_ReturnsNull() {
        List<Person> resultPeople = personService.findByAddress("Baker Street");

        assertThat(resultPeople).isNull();
    }

    private static Stream<Arguments> phoneAndPerson() {
        return Stream.of(
            arguments("333", List.of(
                new Person(3, "Felicia", "Abbey Mews", "333")
            )),
            arguments("999", List.of(
                new Person(5, "Emma", "Abbey Mews", "999")
            )),
            arguments("888", List.of(
                new Person(7, "Emma", "Angel Walk", "888")
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("phoneAndPerson")
    void findByPhone_WhenGivenPhone_ReturnsListOfPersons(String address, List<Person> expectedPeople) {
        List<Person> resultPeople = personService.findByPhone(address);

        assertThat(resultPeople).containsExactlyInAnyOrderElementsOf(expectedPeople);
    }

    @Test
    void findByPhone_WhenGivenInvalidPhone_ReturnsNull() {
        List<Person> resultPeople = personService.findByPhone("987");

        assertThat(resultPeople).isNull();
    }
}
