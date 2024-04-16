package se.lexicon;

import se.lexicon.data.DataStorage;
import java.util.function.*;
public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;
    static Consumer<String> printMe = (str) -> System.out.println(str);
    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<se.lexicon.model.Person> isErik = s -> s.getFirstName().equalsIgnoreCase("Erik");
        java.util.List<se.lexicon.model.Person> pList = storage.findMany(isErik);
        for(se.lexicon.model.Person p : pList){
            printMe.accept(p.getFirstName());
        }

        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<se.lexicon.model.Person> isFemale = s -> s.getGender().toString().equalsIgnoreCase("Female");
        java.util.List<se.lexicon.model.Person> pList = storage.findMany(isFemale);
        for(se.lexicon.model.Person p : pList){
            printMe.accept(p.toString());
        }


        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        //java.time.format.DateTimeFormatter dt = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.time.LocalDate searchDate = java.time.LocalDate.of(2000,1,1);
        Predicate<se.lexicon.model.Person> isBornAfterAndInc = s -> s.getBirthDate().isAfter(searchDate) || s.getBirthDate().isEqual(searchDate);
        java.util.List<se.lexicon.model.Person> pList = storage.findMany(isBornAfterAndInc);
        for(se.lexicon.model.Person p : pList){
            printMe.accept(p.toString());
        }

        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        int pId = 123;
        Predicate <se.lexicon.model.Person> id123 = (p) -> p.getId() == pId;
        System.out.println(storage.findOne(id123).toString());
        //se.lexicon.model.Person p =


        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }
}
