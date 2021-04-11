package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Animal> animals = getAnimals();

        //  The old way (Imperative)
//        List<Animal> predators = new ArrayList<>();
//        for (Animal animal: animals) {
//            if (animal.getClassification().equals(Classification.PREDATOR)){
//                predators.add(animal);
//            }
//        }
//        predators.forEach(System.out::println);

        // New way (Declarative) Filter, Sort, All match, Any match, None match, None match
        //   Max, Min, Group

        //Filter
        List<Animal> predators = animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.PREDATOR))
                .collect(Collectors.toList());

        predators.forEach(System.out::println);


        //Sort
        List<Animal> sortedPredators = animals.stream()
                .sorted(Comparator.comparing(Animal::getAge).reversed()
                        .thenComparing(Animal::getClassification)
                        .reversed())
                .collect(Collectors.toList());

        sortedPredators.forEach(System.out::println);


        //  All match - Checks all entities in the collection are equal to a condition
        boolean allMatch = animals.stream()
                .allMatch(animal -> animal.getAge() > 10);

        System.out.println(allMatch); // false


        // Any match - Checks that at least one entity in the collection is equal to a condition
        boolean anyMatch = animals.stream()
                .anyMatch(animal -> animal.getAge() > 10);

        System.out.println(anyMatch); //true


        // None match - If there is no entity equal to the condition, it displays true
        boolean noneMatch = animals.stream()
                .noneMatch(animal -> animal.getName().equals("Слон"));

        System.out.println(noneMatch); //false


        //Max - Returns optional
        animals.stream()
                .max(Comparator.comparing(Animal::getAge))
                .ifPresent(System.out::println);


        // Min - Returns optional
        animals.stream()
                .min(Comparator.comparing(Animal::getAge))
                .ifPresent(System.out::println);


        // Group
        Map<Classification, List<Animal>> classificationListMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getClassification));

        classificationListMap.forEach(((classification, animals1) -> {
            System.out.println(classification);
            animals1.forEach(System.out::println);
            System.out.println();
        }));


        //Find the name of the oldest predator
        Optional<String> predatorName = animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.PREDATOR))
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getName);

        System.out.println(predatorName);
    }

    private static List<Animal> getAnimals() {
        return List.of(
                new Animal("Слон", 20, Classification.HERBIVOROUS),
                new Animal("Лев", 10, Classification.PREDATOR),
                new Animal("Гиена", 11, Classification.PREDATOR),
                new Animal("Жираф", 7, Classification.HERBIVOROUS),
                new Animal("Гибон", 35, Classification.OMNIVOROUS),
                new Animal("Лошадь", 36, Classification.HERBIVOROUS),
                new Animal("Рысь", 2, Classification.PREDATOR),
                new Animal("Динозавр", 200, Classification.PREDATOR)
        );
    }
}
