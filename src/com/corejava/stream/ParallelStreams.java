package com.corejava.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStreams {

	public static void main(String[] args) throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get("bin/Jane Eyre.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        
        int[] shortWords = new int[10];
        
        //下面是错误的方法，且每次计算结果都不一致还不对
        wordList.parallelStream().forEach(s->{
        	if(s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));
        
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s->{
        	if(s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));
        
        Map<Integer, Long> shortWordCounts = wordList.parallelStream().filter(s->s.length()<10)
        		.collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(shortWordCounts);
        
        Map<Integer, List<String>> result = wordList.parallelStream()
        		.collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(17));
        
        result = wordList.parallelStream()
        		.collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(17));
        
        
        
        Map<Integer, Long> wordCounts = wordList.parallelStream()
        		.collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(wordCounts);
        
        
	}

}
