package edu.hw4.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnimalHandler {
    public static List<Animal> sortAnimalsByHeightAsc(List<Animal> animals) {
        List<Animal> animalList = animals.stream().toList();

        Comparator<Animal> heightComparator = Comparator.comparingInt((Animal animal) -> animal.height());

        animalList = animalList.stream().sorted(heightComparator).toList();

        return animalList;
    }

    public static List<Animal> sortFirstNAnimalsByWeightDesc(List<Animal> animals, int amountOfFirstAnimals) {
        Comparator<Animal> weightComparator =
            (Animal animal1, Animal animal2) -> -(animal1.weight() - animal2.weight());

        return animals.stream().sorted(weightComparator).limit(amountOfFirstAnimals).toList();
    }

    public static Map<Animal.Type, Integer> getAnimalsAmount(List<Animal> animals) {
        return animals.stream().collect(
            Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        Comparator<Animal> nameLengthComparator = Comparator.comparingInt((Animal animal) -> animal.name().length());

        return animals.stream().max(nameLengthComparator).get();
    }

    public static Animal.Sex getMostFrequentSex(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public static Map<Animal.Type, Integer> getAnimalTypeAndMaxWeight(List<Animal> animals) {
        return animals.stream().collect(
            Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Animal::weight)), animal -> animal.get().weight())));
    }

    public static Animal getNOldestAnimal(List<Animal> animals, int placement) {
        if (placement < 1) {
            return null;
        }

        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed())
            .skip(placement - 1)
            .findFirst().get();
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

        if (bitingSpidersAmount > bitingDogsAmount) {
            return true;
        }

        return false;
    }

    public static Animal getHeaviestFish(List<Animal>... animals) {
        return Arrays.stream(animals).flatMap(Collection::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH)).max(Comparator.comparingInt(Animal::weight))
            .get();
    }

    public static Map<String, Set<ValidationError>> getAnimalsWhoseNameHasMistakes(List<Animal> animals) {
        Function<Animal, Set<ValidationError>> checkIfAnimalInfoIsValid = animal -> {
            try {
                checkIfAnimalInfoIsValid(animal);
            } catch (ValidationError error) {
                return new HashSet<>(Arrays.stream(error.getErrors()).toList());
            }

            return null;
        };

        return animals.stream().collect(Collectors.toMap(Animal::name, checkIfAnimalInfoIsValid));
    }

    public static Map<String, String> getAnimalsWhoseNameHasMistakesPrettyPrint(List<Animal> animals) {
        Function<Animal, String> checkIfAnimalInfoIsValid = animal -> {
            try {
                checkIfAnimalInfoIsValid(animal);
            } catch (ValidationError error) {
                ValidationError[] errors = error.getErrors();
                StringBuilder errorsPrint = new StringBuilder();

                for (int i = 0; i < errors.length; i++) {
                    String errorMessage = errors[i].getMessage();

                    if (errorMessage.contains("age")) {
                        errorsPrint.append("Age: " + errorMessage);
                    } else if (errorMessage.contains("height")) {
                        errorsPrint.append("Height: " + errorMessage);
                    } else if (errorMessage.contains("weight")) {
                        errorsPrint.append("Weight: " + errorMessage);
                    } else if (errorMessage.contains("name")) {
                        errorsPrint.append("Name: " + errorMessage);
                    }
                }

                return errorsPrint.toString();
            }

            return null;
        };

        return animals.stream().collect(Collectors.toMap(Animal::name, checkIfAnimalInfoIsValid));
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

        String regexForEngLettersAndDash = "^[a-zA-Z]+(-[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(regexForEngLettersAndDash);
        Matcher matcher = pattern.matcher(animal.name());

        if (!matcher.matches()) {
            errorList.add(new ValidationError("Incorrect name format.", null));
        }

        if (!errorList.isEmpty()) {
            ValidationError[] errors = errorList.toArray(new ValidationError[errorList.size()]);

            throw new ValidationError("Animal information is not valid.", errors);
        }

        return true;
    }

    private AnimalHandler() {
    }
}
