package population;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long underage = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(underage);

        List<String> conscripts = persons.stream()
                .filter(c -> c.getSex() == Sex.MAN)
                .filter(c -> c.getAge() >= 18 && c.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscripts);

        List<Person> workable = persons.stream()
                .filter(w -> w.getAge() >= 18)
                .filter(w -> w.getSex() == Sex.WOMAN && w.getAge() < 60 || w.getSex() == Sex.MAN && w.getAge() < 65)
                .filter(w -> w.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workable);


    }
}
