package edu.hw4;

import edu.hw4.task1.Animal;
import edu.hw4.task1.AnimalHandler;
import edu.hw4.task1.ValidationError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AnimalHandlerTest {

    private static Stream<Arguments> animalsListAndSortedListByHeight() {
        return Stream.of(
            arguments(
                new ArrayList<>(List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false)
                )),
                List.of(
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false),
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndSortedListByHeight")
    void sortAnimalsByHeightAsc_WhenGivenListOfAnimals_SortsAsc(
        List<Animal> animalsList,
        List<Animal> sortedAnimalsList
    ) {
        List<Animal> resultList = AnimalHandler.sortAnimalsByHeightAsc(animalsList);

        assertThat(resultList).containsExactlyElementsOf(sortedAnimalsList);
    }

    private static Stream<Arguments> animalsListAndSortedListByWeight() {
        return Stream.of(
            arguments(
                new ArrayList<>(List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false)
                )),
                List.of(
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndSortedListByWeight")
    void sortFirstNAnimalsByWeightDesc_WhenGivenListOfAnimals_SortsDesc(
        List<Animal> animalsList,
        List<Animal> sortedAnimalsList
    ) {
        List<Animal> resultList = AnimalHandler.sortFirstNAnimalsByWeightDesc(animalsList, 2);

        assertThat(resultList).containsExactlyElementsOf(sortedAnimalsList);
    }

    private static Stream<Arguments> animalsListAndAnimalAmountMap() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 2, 20, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false)
                ),
                new HashMap<>(
                    Map.of(Animal.Type.CAT, 2, Animal.Type.DOG, 2, Animal.Type.BIRD, 1)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalAmountMap")
    void getAnimalsAmount_WhenGivenListOfAnimals_ReturnsAnimalAmountMap(
        List<Animal> animalsList,
        Map<Animal.Type, Integer> animalMap
    ) {
        Map<Animal.Type, Integer> resultMap = AnimalHandler.getAnimalsAmount(animalsList);

        assertThat(resultMap).containsAllEntriesOf(animalMap);
    }

    private static Stream<Arguments> animalsListAndAnimalWithLongestName() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 2, 20, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false)
                ),
                new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalWithLongestName")
    void getAnimalWithLongestName_WhenGivenListOfAnimals_ReturnsAnimalWithLongestName(
        List<Animal> animalsList,
        Animal expectedAnimal
    ) {
        Animal resultAnimal = AnimalHandler.getAnimalWithLongestName(animalsList);

        assertThat(resultAnimal).isEqualTo(expectedAnimal);
    }

    private static Stream<Arguments> animalsListAndMostFrequentSex() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 2, 20, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false)
                ),
                Animal.Sex.M
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndMostFrequentSex")
    void getMostFrequentSex_WhenGivenListOfAnimals_ReturnsMostFrequentSex(
        List<Animal> animalsList,
        Animal.Sex expectedSex
    ) {
        Animal.Sex resultSex = AnimalHandler.getMostFrequentSex(animalsList);

        assertThat(resultSex).isEqualTo(expectedSex);
    }

    private static Stream<Arguments> animalsListAndTypeWeightMap() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 3, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 2, 20, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 2, false)
                ),
                Map.of(Animal.Type.CAT, 5, Animal.Type.DOG, 20, Animal.Type.BIRD, 2)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndTypeWeightMap")
    void getAnimalTypeAndMaxWeight_WhenGivenListOfAnimals_ReturnsTypeWeightMap(
        List<Animal> animalsList,
        Map<Animal.Type, Integer> expectedMap
    ) {
        Map<Animal.Type, Integer> resultMap = AnimalHandler.getAnimalTypeAndMaxWeight(animalsList);

        assertThat(resultMap).containsAllEntriesOf(expectedMap);
    }

    private static Stream<Arguments> animalsListAndFourthOldestAnimal() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 2, 20, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false)
                ),
                new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 2, 20, 4, true)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndFourthOldestAnimal")
    void getNOldestAnimal_WhenGivenListOfAnimals_ReturnsFourthOldestAnimal(
        List<Animal> animalsList,
        Animal expectedAnimal
    ) {
        Animal resultAnimal = AnimalHandler.getNOldestAnimal(animalsList, 4);

        assertThat(resultAnimal).isEqualTo(expectedAnimal);
    }

    private static Stream<Arguments> animalsListAndHeaviestAnimalWithHeightLessThanTen() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false)
                ),
                Optional.of(new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true))
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndHeaviestAnimalWithHeightLessThanTen")
    void getHeaviestAnimalWithHeightLessThan_WhenGivenListOfAnimals_ReturnsHeaviestAnimalWithHeightLessThan10(
        List<Animal> animalsList,
        Optional<Animal> expectedAnimal
    ) {
        Optional<Animal> resultAnimal = AnimalHandler.getHeaviestAnimalWithHeightLessThan(animalsList, 10);

        assertThat(resultAnimal).isEqualTo(expectedAnimal);
    }

    private static Stream<Arguments> animalsListAndPawsAmount() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 1, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 1, 8, 1, false),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false)
                ),
                20
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndPawsAmount")
    void getPawsAmount_WhenGivenListOfAnimals_ReturnsPawsAmount(
        List<Animal> animalsList,
        Integer expectedPawsAmount
    ) {
        Integer resultPawsAmount = AnimalHandler.getPawsAmount(animalsList);

        assertThat(resultPawsAmount).isEqualTo(expectedPawsAmount);
    }

    private static Stream<Arguments> animalsListAndAnimalsWhoseAgeNotEqualPawsAmount() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 4, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 2, 8, 1, false),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false)
                ),
                List.of(
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 50, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalsWhoseAgeNotEqualPawsAmount")
    void getAnimalsWhoseAgeNotEqualPawsAmount_WhenGivenListOfAnimals_ReturnsAnimalsWhoseAgeNotEqualPawsAmount(
        List<Animal> animalsList,
        List<Animal> expectedList
    ) {
        List<Animal> resultList = AnimalHandler.getAnimalsWhoseAgeNotEqualPawsAmount(animalsList);

        assertThat(resultList).containsAll(expectedList);
    }

    private static Stream<Arguments> animalsListAndAnimalsThatCanBiteAndHeightMoreThan100() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 4, 20, 5, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 150, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 150, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 2, 8, 1, false),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false)
                ),
                List.of(
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 150, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 150, 10, true)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalsThatCanBiteAndHeightMoreThan100")
    void getAnimalsThatCanBiteAndHeightMoreThan100_WhenGivenListOfAnimals_ReturnsAnimalsThatCanBiteAndHeightMoreThan100(
        List<Animal> animalsList,
        List<Animal> expectedList
    ) {
        List<Animal> resultList = AnimalHandler.getAnimalsThatCanBiteAndHeightMoreThan100(animalsList);

        assertThat(resultList).containsAll(expectedList);
    }

    private static Stream<Arguments> animalsListAndAnimalsAmountWhoseWeightMoreThanHeight() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 150, 10, true),
                    new Animal("Mia", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 2, 8, 10, false),
                    new Animal("Donut", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false)
                ),
                2
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalsAmountWhoseWeightMoreThanHeight")
    void getAnimalsAmountWhoseWeightMoreThanHeight_WhenGivenListOfAnimals_ReturnsAnimalsAmountWhoseWeightMoreThanHeight(
        List<Animal> animalsList,
        Integer expectedAnimalsAmount
    ) {
        Integer resultAnimalsAmount = AnimalHandler.getAnimalsAmountWhoseWeightMoreThanHeight(animalsList);

        assertThat(resultAnimalsAmount).isEqualTo(expectedAnimalsAmount);
    }

    private static Stream<Arguments> animalsListAndAnimalsWhoHaveMoreThanTwoWordsName() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 150, 10, true),
                    new Animal("Mia La Rose", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 2, 8, 10, false),
                    new Animal("Dunkin Donut Senior", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false)
                ),
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Mia La Rose", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Dunkin Donut Senior", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalsWhoHaveMoreThanTwoWordsName")
    void getAnimalsWhoHaveMoreThanTwoWordsName_WhenGivenListOfAnimals_ReturnsAnimalsWhoHaveMoreThanTwoWordsName(
        List<Animal> animalsList,
        List<Animal> expectedAnimals
    ) {
        List<Animal> resultAnimals = AnimalHandler.getAnimalsWhoHaveMoreThanTwoWordsName(animalsList);

        assertThat(resultAnimals).isEqualTo(expectedAnimals);
    }

    private static Stream<Arguments> animalsListAndDogWithHeightMoreThan60Present() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 5, 150, 10, true),
                    new Animal("Mia La Rose", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 2, 8, 10, false),
                    new Animal("Dunkin Donut Senior", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndDogWithHeightMoreThan60Present")
    void isDogWithHeightMoreThanNPresent_WhenGivenListOfAnimals_ReturnsIsDogWithHeightMoreThan60Present(
        List<Animal> animalsList
    ) {
        Boolean resultIsPresent = AnimalHandler.isDogWithHeightMoreThanNPresent(animalsList, 60);

        assertThat(resultIsPresent).isTrue();
    }

    private static Stream<Arguments> animalsListAndWeightSumOfAnimalsWhoseAgeBetween2And6() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 8, 150, 10, true),
                    new Animal("Mia La Rose", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 3, 8, 10, false),
                    new Animal("Dunkin Donut Senior", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false)
                ),
                Map.of(Animal.Type.CAT, 50, Animal.Type.DOG, 20, Animal.Type.BIRD, 12)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndWeightSumOfAnimalsWhoseAgeBetween2And6")
    void getWeightSumOfAnimalsWhoseAgeInRange_WhenGivenListOfAnimals_ReturnsWeightSumOfAnimalsWhoseAgeBetween2And6(
        List<Animal> animalsList,
        Map<Animal.Type, Integer> expectedMap
    ) {
        Map<Animal.Type, Integer> resultMap =
            AnimalHandler.getWeightSumOfAnimalsWhoseAgeInRange(animalsList, 2, 6);

        assertThat(resultMap).containsAllEntriesOf(expectedMap);
    }

    private static Stream<Arguments> animalsListAndAnimalsSortedByTypeSexName() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 8, 150, 10, true),
                    new Animal("Mia La Rose", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 3, 8, 10, false),
                    new Animal("Dunkin Donut Senior", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false)
                ),
                List.of(
                    new Animal("Chipper", Animal.Type.BIRD, Animal.Sex.F, 3, 8, 10, false),
                    new Animal("Dunkin Donut Senior", Animal.Type.BIRD, Animal.Sex.F, 4, 108, 2, false),
                    new Animal("Raven Claw Junior", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Mia La Rose", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Roger", Animal.Type.DOG, Animal.Sex.M, 8, 150, 10, true)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalsSortedByTypeSexName")
    void getAnimalsSortedByTypeSexName_WhenGivenListOfAnimals_ReturnsAnimalsSortedByTypeSexName(
        List<Animal> animalsList,
        List<Animal> expectedList
    ) {
        List<Animal> resultList = AnimalHandler.getAnimalsSortedByTypeSexName(animalsList);

        assertThat(resultList).containsExactlyElementsOf(expectedList);
    }

    private static Stream<Arguments> animalsListWithSpidersAndDogs() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.CAT, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Shazam", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Shadow", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Jumper", Animal.Type.SPIDER, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Feather", Animal.Type.SPIDER, Animal.Sex.M, 3, 50, 20, false)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListWithSpidersAndDogs")
    void areSpidersMoreProneToBiteThanDogs_WhenGivenListOfAnimals_ReturnsAreSpidersMoreProneToBiteThanDogs(
        List<Animal> animalsList
    ) {
        Boolean resultAreSpidersBitingMore = AnimalHandler.areSpidersMoreProneToBiteThanDogs(animalsList);

        assertThat(resultAreSpidersBitingMore).isFalse();
    }

    private static Stream<Arguments> animalsListAndHeaviestFish() {
        return Stream.of(
            arguments(
                List.of(
                    List.of(
                    new Animal("Raven Claw Junior", Animal.Type.FISH, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Shazam", Animal.Type.FISH, Animal.Sex.M, 3, 50, 20, true)
                    ),
                    List.of(
                        new Animal("Shadow", Animal.Type.DOG, Animal.Sex.M, 3, 50, 120, true),
                        new Animal("Jumper", Animal.Type.FISH, Animal.Sex.M, 3, 50, 220, true),
                        new Animal("Feather", Animal.Type.FISH, Animal.Sex.M, 3, 50, 320, false)
                    )
                ),
                new Animal("Feather", Animal.Type.FISH, Animal.Sex.M, 3, 50, 320, false)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndHeaviestFish")
    void getHeaviestFish_WhenGivenListOfAnimals_ReturnsHeaviestFish(
        List<List<Animal>> animalsList,
        Animal expectedFish
    ) {
        Animal resultFish = AnimalHandler.getHeaviestFish(animalsList);

        assertThat(resultFish).isEqualTo(expectedFish);
    }

    private static Stream<Arguments> animalsListAndAnimalsWhoseNameHasMistakesMap() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.FISH, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy-", Animal.Type.DOG, Animal.Sex.M, -3, -50, 20, true),
                    new Animal("Shazam", Animal.Type.FISH, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Shadow", Animal.Type.DOG, Animal.Sex.M, 3, 50, 120, true),
                    new Animal("Jumper", Animal.Type.FISH, Animal.Sex.M, 3, 50, 220, true),
                    new Animal("Fea99ther", Animal.Type.FISH, Animal.Sex.M, 3, -50, 320, false)
                ),

                Map.of(
                    "Fea99ther",
                    Set.of(
                        new ValidationError("Negative height value.", null),
                        new ValidationError("Incorrect name format.", null)
                    ),
                    "Poppy-",
                    Set.of(
                        new ValidationError("Negative age value.", null),
                        new ValidationError("Negative height value.", null),
                        new ValidationError("Incorrect name format.", null)
                    )

                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalsWhoseNameHasMistakesMap")
    void getAnimalsWhoseNameHasMistakes_WhenGivenListOfAnimals_ReturnsAnimalsWhoseNameHasMistakes(
        List<Animal> animalsList,
        Map<String, Set<ValidationError>> expectedMap
    ) {
        Map<String, Set<ValidationError>> resultMap = AnimalHandler.getAnimalsWhoseNameHasMistakes(animalsList);

        assertThat(resultMap).containsExactlyInAnyOrderEntriesOf(expectedMap);
    }

    private static Stream<Arguments> animalsListAndAnimalsWhoseNameHasMistakesWithStringErrorsMap() {
        return Stream.of(
            arguments(
                List.of(
                    new Animal("Raven Claw Junior", Animal.Type.FISH, Animal.Sex.F, 4, 20, 50, true),
                    new Animal("Poppy-", Animal.Type.DOG, Animal.Sex.M, -3, -50, 20, true),
                    new Animal("Shazam", Animal.Type.FISH, Animal.Sex.M, 3, 50, 20, true),
                    new Animal("Shadow", Animal.Type.DOG, Animal.Sex.M, 3, 50, 120, true),
                    new Animal("Jumper", Animal.Type.FISH, Animal.Sex.M, 3, 50, 220, true),
                    new Animal("Fea99ther", Animal.Type.FISH, Animal.Sex.M, 3, -50, 320, false)
                ),

                Map.of(
                    "Fea99ther", "Height: " + "Negative height value." + "Name: " + "Incorrect name format.",
                    "Poppy-", "Age: " + "Negative age value."
                        + "Height: " + "Negative height value."
                        + "Name: " + "Incorrect name format."
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("animalsListAndAnimalsWhoseNameHasMistakesWithStringErrorsMap")
    void getAnimalsWhoseNameHasMistakesPrettyPrint_WhenGivenListOfAnimals_ReturnsAnimalsWhoseNameHasMistakesPrettyPrint(
        List<Animal> animalsList,
        Map<String, String> expectedMap
    ) {
        Map<String, String> resultMap = AnimalHandler.getAnimalsWhoseNameHasMistakesPrettyPrint(animalsList);

        assertThat(resultMap).containsExactlyInAnyOrderEntriesOf(expectedMap);
    }

}
