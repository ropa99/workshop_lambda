package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java.util.Collections;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl() {
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
       Person retPerson = null;
        for(Person p : personList){
            if(filter.test(p)){
                retPerson =  p;
            }
        }
        return retPerson;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {
        String retString = null;
        for(Person p : personList){
            if(filter.test(p)){
                retString = personToString.apply(p);
            }
        }
        return retString;
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString) {
        List<String> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(personToString.apply(person));
            }
        }
        return result;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer) {

        for(Person p : personList){
            if(filter.test(p)){
                consumer.accept(p); //TODO: Kolla vad den ska göra ?? .andThen()
            }
        }
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {
        Collections.sort(personList,comparator);
        //personList.sort(comparator);
        return personList;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {
        List<Person> result = new ArrayList<>();
        for(Person p : personList){
            if(filter.test(p)){
                result.add(p);
            }
        }
        Collections.sort(result,comparator);
        return result;
    }
}
