package com.test.collections;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrConvertTest {
    public static void main(String[] args) {
        String[] arr = "1, 2, 3, 4, 5, 6".split(", ");
        int[] arr_int = Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
        System.out.println(Arrays.toString(arr_int));
        int[] arr_int2 = Arrays.stream(arr_int).map(x->x+1).toArray();
        System.out.println(Arrays.toString(arr_int2));
        Integer[] arr_Int = Arrays.stream(arr_int).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr_Int));

        Integer[] arr_Int2 = Arrays.stream(arr_int).map(x->x*10).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr_Int2));
    }
}
