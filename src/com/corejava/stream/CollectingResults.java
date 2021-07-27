package com.corejava.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
	public static Stream<String> noVowels() throws IOException{
		String contents = new String(Files.readAllBytes(Paths.get("bin/Jane Eyre.txt")), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("\\PL+"));
		Stream<String> words = wordList.stream();
		return words.map(s -> s.replace("[aeiouAEIOU]", ""));
	}
	
	public static <T> void ShowAction(String label, Set<T> set) {
		System.out.print(label + ": " + set.getClass().getName());
		System.out.println("[" + set.stream().limit(10).map(Object::toString)
				.collect(Collectors.joining(", ")) + "]");
	}
	public static void main(String[] args) throws IOException{
		Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
		System.out.println("Object array: " + numbers); // Object[] array;
		
		try {
			Integer number = (Integer)numbers[0];
			System.out.println("number: " + number);
			System.out.println("下面的语句将抛出异常");
			Integer[] number2 = (Integer[]) numbers;
		}catch (ClassCastException e) {
			System.out.println(e);
		}
		
		Integer[] number3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
		System.out.println("Integer array: " + number3);
		
		Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
		ShowAction("noVowelSet: ", noVowelSet);
		
		TreeSet<String> noVoweTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
		ShowAction("noVowelTreeSet", noVoweTreeSet);
		
		String result = noVowels().limit(10).collect(Collectors.joining());
		System.out.println("join: " + result);
		result = noVowels().limit(10).collect(Collectors.joining(", "));
		System.out.println("join with commas: " + result);
		
		IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
		double averageWordLength = summary.getAverage();
		double maxWordLength = summary.getMax();
		System.out.println("Average word lenght: " + averageWordLength);
		System.out.println("Max word length: " + maxWordLength);
		System.out.println("for Each: ");
		noVowels().limit(10).forEach(System.out::println);
		
	}

}
