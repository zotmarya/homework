package edu.hw4.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("MagicNumber")
public class AnimalHandler {
    public static List<Animal> sortAnimalsByHeightAsc(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> sortFirstNAnimalsByWeightDesc(List<Animal> animals, int amountOfFirstAnimals) {
        return animals.stream().sorted((Animal animal1, Animal animal2) -> -(animal1.weight() - animal2.weight()))
            .limit(amountOfFirstAnimals).toList();
    }

    public static Map<Animal.Type, Integer> getAnimalsAmount(List<Animal> animals) {
        return animals.stream().collect(
            Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt((Animal animal) -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex getMostFrequentSex(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public static Map<Animal.Type, Integer> getAnimalTypeAndMaxWeight(List<Animal> animals) {
        return animals.stream().collect(
            Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Animal::weight)), animal -> animal.orElse(null).weight())));
    }

    public static Animal getNOldestAnimal(List<Animal> animals, int placement) {
        if (placement < 1) {
            return null;
        }

        return animals.stream().sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(placement - 1)
            .findFirst().orElse(null);
    }

    public static Optional<Animal> getHeaviestAnimalWithHeightLessThan(List<Animal> animals, int heightBound) {
        return animals.stream().filter(animal -> animal.height() < heightBound)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer getPawsAmount(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> getAnimalsWhoseAgeNotEqualPawsAmount(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    public static List<Animal> getAnimalsThatCanBiteAndHeightMoreThan100(List<Animal> animals) {
        return animals.stream().filter(animal ->
            animal.bites() && animal.height() > 100).toList();
    }

    public static Integer getAnimalsAmountWhoseWeightMoreThanHeight(List<Animal> animals) {
        return (int) animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    public static List<Animal> getAnimalsWhoHaveMoreThanTwoWordsName(List<Animal> animals) {
        return animals.stream().filter(animal -> {
            String[] names = animal.name().split("\\s+");
            return names.length > 2;
        }).toList();
    }

    public static Boolean isDogWithHeightMoreThanNPresent(List<Animal> animals, int heightLowerBound) {
        return animals.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > heightLowerBound);
    }

    public static Map<Animal.Type, Integer> getWeightSumOfAnimalsWhoseAgeInRange(
        List<Animal> animals, int ageLowerBound, int ageUpperBound
    ) {

        return animals.stream().filter(animal -> animal.age() > ageLowerBound && animal.age() < ageUpperBound)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> getAnimalsSortedByTypeSexName(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::type)
            .thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    public static Boolean areSpidersMoreProneToBiteThanDogs(List<Animal> animals) {
        long bitingSpidersAmount = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.SPIDER) && animal.bites()).count();

        long bitingDogsAmount = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG) && animal.bites()).count();

        return bitingSpidersAmount > bitingDogsAmount;
    }

    public static Animal getHeaviestFish(List<Animal>... animals) {
        return Arrays.stream(animals).flatMap(Collection::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH)).max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> getAnimalsWhoseNameHasMistakes(List<Animal> animals) {
        Function<Animal, Set<ValidationError>> functionAnimalInfoIsValid = animal -> {
            try {
                checkIfAnimalInfoIsValid(animal);
                return Collections.emptySet();
            } catch (ValidationError error) {
                return new HashSet<>(Arrays.stream(error.getErrors()).toList());
            }
        };

        return animals.stream()
            .collect(Collectors.toMap(Animal::name, functionAnimalInfoIsValid))
            .entrySet().stream().filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> getAnimalsWhoseNameHasMistakesPrettyPrint(List<Animal> animals) {
        Function<Animal, String> functionAnimalInfoIsValid = animal -> {
            try {
                checkIfAnimalInfoIsValid(animal);
            } catch (ValidationError error) {
                ValidationError[] errors = error.getErrors();
                StringBuilder errorsPrint = new StringBuilder();

                for (ValidationError validationError : errors) {
                    String errorMessage = validationError.getMessage();

                    if (errorMessage.contains("age")) {
                        errorsPrint.append("Age: ").append(errorMessage);
                    } else if (errorMessage.contains("height")) {
                        errorsPrint.append("Height: ").append(errorMessage);
                    } else if (errorMessage.contains("weight")) {
                        errorsPrint.append("Weight: ").append(errorMessage);
                    } else if (errorMessage.contains("name")) {
                        errorsPrint.append("Name: ").append(errorMessage);
                    }
                }

                return errorsPrint.toString();
            }

            return "";
        };

        return animals.stream().collect(Collectors.toMap(Animal::name, functionAnimalInfoIsValid))
            .entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean checkIfAnimalInfoIsValid(Animal animal) throws ValidationError {
        List<ValidationError> errorList = new ArrayList<>();

        if (animal.age() < 0) {
            errorList.add(new ValidationError("Negative age value.", null));
        }

        if (animal.height() < 0) {
            errorList.add(new ValidationError("Negative height value.", null));
        }

        if (animal.weight() < 0) {
            errorList.add(new ValidationError("Negative weight value.", null));
        }

        String regexForEngLettersAndDash = "^[a-zA-Z]+(-[a-zA-Z]+)*( [a-zA-Z]+(-[a-zA-Z]+)*)*$";
        Pattern pattern = Pattern.compile(regexForEngLettersAndDash);
        Matcher matcher = pattern.matcher(animal.name());

        if (!matcher.matches()) {
            errorList.add(new ValidationError("Incorrect name format.", null));
        }

        if (!errorList.isEmpty()) {
            ValidationError[] errors;
            errors = errorList.toArray(new ValidationError[0]);

            throw new ValidationError("Animal information is not valid.", errors);
        }

        return true;
    }

    private AnimalHandler() {
    }
}
