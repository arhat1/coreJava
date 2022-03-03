package com.test.stream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static java.lang.System.out;

public class StreamTest {
    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\arhat\\IdeaProjects\\Java8Test\\out\\production\\Java8Test\\Hamlet.txt";
        String contents = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        out.println(contents);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        long count = words.stream().filter(w->w.length() >12).count();
        out.println(count);
        String[] list = words.stream().filter(w->w.length() >10).limit(5).toArray(String[]::new);
        out.println(Arrays.toString(list));
        String[] upper = Arrays.stream(list).map(String::toUpperCase).toArray(String[]::new);
        out.println(Arrays.toString(upper));


        int[] arr_int = {1,2,3,4};
        Integer[] arr_Int = Arrays.stream(arr_int).boxed().toArray(Integer[]::new);
        out.println(Arrays.toString(arr_Int));
        String[] arr_str = Arrays.stream(arr_int).boxed().map(String::valueOf).toArray(String[]::new);
        out.println(Arrays.toString(arr_str));

        int[] arr_int2 = Arrays.stream(arr_Int).mapToInt(w->Integer.valueOf(w) *10).toArray();
        out.println(Arrays.toString(arr_int2));

        Stream<Double> randoms = Stream.generate(Math::random).limit(4);
        out.println(Arrays.toString(randoms.mapToDouble(Double::valueOf).toArray()));

        Stream<Integer> integers = Stream.iterate(0, n->n +1).limit(10);
        out.println(Arrays.toString(integers.toArray()));

        Stream<String> words2 = Pattern.compile("\\PL+").splitAsStream(contents).limit(10);
        out.println(Arrays.toString(words2.toArray()));

        Stream<String> lines = Files.lines(Paths.get(fileName)).limit(3);
        out.println(Arrays.toString(lines.toArray()));









    }
}
