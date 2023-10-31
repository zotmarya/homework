package edu.hw4.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch(type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public List<Animal> sortAnimalsByHeightAsc(List<Animal> animals) {
        List<Animal> animalList = animals.stream().toList();

        Comparator<Animal> heightComparator = Comparator.comparingInt((Animal animal) -> animal.height);

        animalList = animalList.stream().sorted(heightComparator).toList();

        return animalList;
    }

    public List<Animal> sortFirstNAnimalsByWeightDesc(List<Animal> animals, int amountOfFirstAnimals) {
        List<Animal> animalList = animals.subList(0, amountOfFirstAnimals);

        Comparator<Animal> weightComparator = (Animal animal1, Animal animal2) -> -(animal1.weight-animal2.weight);

        animalList = animalList.stream().sorted(weightComparator).toList();

        return animalList;
    }

    public Map<Type, Integer> getAnimalsAmount(List<Animal> animals) {
        Map<Type, Integer> animalAmountMap = animals.stream().collect(
            Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));

//        for(Animal animal : animals) {
//            if(animalAmountMap.get(animal.type) == null) {
//                animalAmountMap.put(animal.type, 1);
//            } else {
//                int amount = animalAmountMap.get(animal.type);
//                animalAmountMap.put(animal.type, ++amount);
//            }
//        }



        return animalAmountMap;
    }

    public Animal getAnimalWithLongestName(List<Animal> animals) {
        Comparator<Animal> nameLengthComparator = Comparator.comparingInt((Animal animal) -> animal.name.length());

        return animals.stream().max(nameLengthComparator).get();
    }

    public Sex getMostFrequentSex(List<Animal> animals) {
//        int femaleAmount = 0;
//        int maleAmount = 0;
//
//        for(Animal animal: animals) {
//            if(animal.sex.equals(Sex.F)) {
//                femaleAmount++;
//            } else {
//                maleAmount++;
//            }
//        }
//
//        Sex frequentSex;
//
//        if(femaleAmount > maleAmount) {
//            frequentSex = Sex.F;
//        } else {
//            frequentSex = Sex.M;
//        }

        Sex frequentSex = animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);

        return frequentSex;
    }

    public Map<Type, Integer> getAnimalTypeAndMaxWeight(List<Animal> animals) {
        Map<Type, Integer> animalTypeMaxWeightmap = new HashMap<>();

//        for(Animal animal: animals) {
//            int weight = animal.weight;
//            Integer previousWeight = animalTypeMaxWeightmap.get(animal.type);
//
//            if(previousWeight == null || weight > previousWeight) {
//                animalTypeMaxWeightmap.put(animal.type, weight);
//            }
//        }

        animalTypeMaxWeightmap = animals.stream().collect(
            Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingInt(Animal::weight)), animal -> animal.get().weight())));

        return animalTypeMaxWeightmap;
    }

    public Animal getNOldestAnimal(List<Animal> animals, int placement) {
        if(placement<1) {
            return null;
        }

        List<Animal> animalList = animals.stream().toList();

//        Comparator<Animal> comparatorAgeDesc = (Animal animal1, Animal animal2) -> -(animal1.age- animal2.age);
//
//        animalList.sort(comparatorAgeDesc);

        Animal animal = animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed())
            .skip(placement-1)
            .findFirst().get();

        return animalList.get(placement-1);
    }

    public Optional<Animal> getHeaviestAnimalWithHeightLessThan(List<Animal> animals, int heightBound) {
        return animals.stream().filter(animal -> animal.height() < heightBound)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public Integer getPawsAmount(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public List<Animal> getAnimalsWhoseAgeNotEqualPawsAmount(List<Animal> animals) {


        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }



}
