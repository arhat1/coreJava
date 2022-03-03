package com.test.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedListTest {
    public static void main(String[] args) {
        //List<String> lst = new LinkedList<>();
        //List<String> lst = new ArrayList<>();
        Queue<String> lst = new LinkedList<>();
        lst.add("aa");
        lst.add("bb");
        lst.add("cc");
        System.out.println(lst);
        //System.out.println(lst.get(0));
        System.out.println(lst.poll());
        System.out.println(lst.poll());
        System.out.println(lst.poll());
        System.out.println(lst.size());




    }
}
