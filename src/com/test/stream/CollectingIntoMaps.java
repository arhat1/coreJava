package com.test.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.lang.System.out;
public class CollectingIntoMaps {
    public static class Person{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getClass().getName() + "[id=" + id + ",name= " + name +"]";
        }

        public Person(int id, String name){
            this.id = id;
            this.name = name;
        }
    }
    public static Stream<Person> people(){
        return Stream.of(new Person(1001, "Perter"), new Person(1002, "Paul"), new Person(1003, "Mary"));
    }

    public static void main(String[] args) {
        Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
        out.println("idToName: "+ idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
        out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity(), (existingValue, newValue)->{
            throw new IllegalStateException();
        }, TreeMap::new));
        out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(Collectors.toMap(
                Locale::getDisplayLanguage,
                l->l.getDisplayLanguage(l),
                (existingValue, newValue)-> existingValue
        ));
        out.println("languageNames: " + languageNames);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguageSets = locales.collect(
                Collectors.toMap(Locale::getDisplayCountry,
                        l-> Collections.singleton(l.getDisplayLanguage()),
                        (a, b)->{
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }
        ));
        out.println("countryLanguageSet: " +countryLanguageSets);
    }
}
