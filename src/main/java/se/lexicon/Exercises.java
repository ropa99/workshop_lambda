package se.lexicon;

import se.lexicon.data.DataStorage;
import java.util.function.*;
import se.lexicon.model.*;
import java.util.List;
import java.time.LocalDate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;
    static Consumer<String> printMe = (str) -> System.out.println(str);
    //static Consumer<Person> printMe = (person) -> System.out.println(person.toString());

    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> isErik = s -> s.getFirstName().equalsIgnoreCase("Erik");
        List<Person> pList = storage.findMany(isErik);
        for(Person p : pList){
            printMe.accept(p.getFirstName());
        }

        System.out.println("----------------------");
    }

    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> isFemale = s -> s.getGender().toString().equalsIgnoreCase("Female");

        List<Person> pList = storage.findMany(isFemale);
        for(se.lexicon.model.Person p : pList){
            printMe.accept(p.toString());
        }


        System.out.println("----------------------");
    }


    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here

        LocalDate searchDate = LocalDate.of(2000,1,1);
        Predicate<Person> isBornAfterAndInc = s -> s.getBirthDate().isAfter(searchDate) || s.getBirthDate().isEqual(searchDate);
        List<Person> pList = storage.findMany(isBornAfterAndInc);
        for(Person p : pList){
            printMe.accept(p.toString());
        }

        System.out.println("----------------------");
    }


    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        int pId = 123;
        Predicate <Person> id123 = (p) -> p.getId() == pId;
        printMe.accept(storage.findOne(id123).toString());

        System.out.println("----------------------");

    }


    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        int pId = 456;
        Predicate <Person> id456 = (p) ->  p.getId() == pId;
        Function<Person,String> strFunc = Person::toString;

        Person p = storage.findOne(id456);
        p.setFirstName("Nisse");
        p.setLastName("Nilsson");
        p.setBirthDate(LocalDate.of(1999,9,9));

        printMe.accept(storage.findOneAndMapToString(id456,strFunc));

        System.out.println("----------------------");
    }


    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findMalesWithE = s -> s.getFirstName().startsWith("E") && s.getGender().equals(se.lexicon.model.Gender.MALE);
        Function<Person,String> strFunc = Person::toString;

        List<String> sList=storage.findManyAndMapEachToString(findMalesWithE,strFunc);
        for(String  s : sList){
            printMe.accept(s);
        }

        System.out.println("----------------------");
    }


    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        storage.findManyAndMapEachToString(
                person -> java.time.Period.between(person.getBirthDate(), LocalDate.now()).getYears() < 10,
                person -> person.getFirstName() + " " + person.getLastName() + " " + java.time.Period.between(person.getBirthDate(), LocalDate.now()).getYears() + " years"
        ).forEach(System.out::println);

                System.out.println("----------------------");
    }


    public static void exercise8(String message) {
        System.out.println(message);
        storage.findAndDo(
                person -> person.getFirstName().equals("Ulf"),
                System.out::println
        );

        System.out.println("----------------------");
    }


    public static void exercise9(String message) {
        System.out.println(message);
        storage.findAndDo(
                person -> person.getLastName().toLowerCase().contains(person.getFirstName().toLowerCase()),
                System.out::println
        );

        System.out.println("----------------------");
    }


    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        storage.findAndDo(
                person -> person.getFirstName().equalsIgnoreCase(new StringBuilder(person.getFirstName()).reverse().toString()),
                person -> System.out.println(person.getFirstName() + " " + person.getLastName())
        );

        System.out.println("----------------------");
    }


    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        storage.findAndSort(
                person -> person.getFirstName().startsWith("A"),
                java.util.Comparator.comparing(Person::getBirthDate)
        ).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        storage.findAndSort(
                person -> person.getBirthDate().getYear() < 1950,
                java.util.Comparator.comparing(Person::getBirthDate).reversed()
        ).forEach(System.out::println);


        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        //With Anonymous inner Class
        java.util.Comparator<se.lexicon.model.Person> compareLastName = new java.util.Comparator<se.lexicon.model.Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        };

        //With Lambda
        java.util.Comparator<se.lexicon.model.Person> compareFirstName = (Person o1, Person o2) -> o1.getFirstName().compareTo(o2.getFirstName());
        //With Method Reference
        java.util.Comparator<se.lexicon.model.Person> compareBirthDate = java.util.Comparator.comparing(Person::getBirthDate);
        //Stack
        java.util.Comparator<se.lexicon.model.Person> all = compareLastName.thenComparing(compareFirstName).thenComparing(compareBirthDate);
        storage.findAndSort(all).forEach(System.out::println);

        /*
        storage.findAndSort(
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate)
        ).forEach(System.out::println);
        */

        System.out.println("----------------------");
    }
}
