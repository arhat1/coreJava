package com.corejava.stream;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingIntoMaps {
	public static class Person{
		private int id;
		private String name;
		public Person(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return  getClass().getName() + "Person [id=" + id + ", name=" + name + "]";
		}
		
	}
	
	public static Stream<Person> people(){
		return Stream.of(new Person(1001, "张三"), new Person(1002, "李四"), new Person(1003, "王麻子"));
	}
	public static void main(String[] args) {
		Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
		System.out.println("idToName: " + idToName);
		
		Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
		System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
		
		idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity(), (existingValue, newValue) -> {
			throw new IllegalStateException();
		}, TreeMap::new));
		System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
		
		Stream<Locale> locales =Stream.of(Locale.getAvailableLocales());
		Map<String, String> languageNames = locales.collect(Collectors.toMap(Locale::getDisplayName,
				l -> l.getDisplayLanguage(l),
				(existingValue, newValue) -> existingValue));
		System.out.println("languageNames: " + languageNames);
		
		 locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryLanguageSets = locales.collect(Collectors.toMap(Locale::getDisplayName, 
				l -> Collections.singleton(l.getDisplayName()), (a,b) -> {
					Set<String> union  = new HashSet<>(a);
					union.addAll(b);
					return union;
				}));
		System.out.println("countryLanguageSets: " + countryLanguageSets);

		
		 

	}

}
