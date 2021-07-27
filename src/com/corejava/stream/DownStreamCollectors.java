package com.corejava.stream;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.spi.LocaleServiceProvider;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DownStreamCollectors {
	public static class City{
		private String name;
		private String state;
		private int population;
		public String getName() {
			return name;
		}
		public String getState() {
			return state;
		}
		public int getPopulation() {
			return population;
		}
		public City(String name, String state, int population) {
			super();
			this.name = name;
			this.state = state;
			this.population = population;
		}
		
	}
	
	public static Stream<City> readCities(){
		return Stream.of(new City("北京","北京", 2189)
				,new City("重庆", "重庆", 3205)
				,new City("上海", "上海", 2487)
				,new City("深圳", "广东", 1756)
				,new City("广州", "广东", 1867)
				,new City("成都", "四川", 1658)
				,new City("天津", "天津", 1386)
				,new City("西安", "陕西", 1295)
				,new City("东莞", "广东", 1046)
				,new City("佛山", "广东", 949)
				,new City("郑州", "河南", 1260)
				,new City("杭州", "浙江", 1193)
				,new City("武汉", "湖北", 1121)
				,new City("惠州", "广东", 601)
				,new City("哈尔滨", "黑龙江", 1706)
				,new City("齐齐哈尔", "黑龙江", 526));
	}
	
	public static void main(String[] args) {
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<Locale>> countryToLocaleSet = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
		System.out.println("CountryToLocaleSet: " + countryToLocaleSet);
		
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Long> countryToLocaleCounts = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
		System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);
		
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryToLanguages = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry
				, Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
		System.out.println("countryToLanguages: " + countryToLanguages);
		
		Stream<City> cities = readCities();
		Map<String, Integer> stateToCityPopulation = cities.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
		System.out.println("stateToCityPopulation: " + stateToCityPopulation);
		
		cities = readCities();
		Map<String, Optional<String>> stateToLongestCityName = cities.collect(Collectors.groupingBy(City::getState,
				Collectors.mapping(City::getName, Collectors.maxBy(Comparator.comparing(String::length)))));
		System.out.println("stateToLongestCityName: " + stateToLongestCityName);
		
		cities = readCities();
		Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(Collectors.groupingBy(City::getState,
				Collectors.summarizingInt(City::getPopulation)));
		System.out.println(stateToCityPopulationSummary.get("广东"));
		
		cities = readCities();
		Map<String, String> stateToCityNames = cities.collect(Collectors.groupingBy(City::getState,
				Collectors.reducing("", City::getName, (s,t)-> s.length() == 0? t : s + ", " + t)));
		System.out.println("statetoCityNames: " + stateToCityNames);	
		
		cities = readCities();
		stateToCityNames = cities.collect(Collectors.groupingBy(City::getState
				,Collectors.mapping(City::getName, Collectors.joining(", "))));
		System.out.println("statetoCityNames: " + stateToCityNames);	
	}

}
