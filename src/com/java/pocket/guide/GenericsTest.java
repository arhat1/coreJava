package com.java.pocket.guide;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        numberList.add((byte)27);
        numberList.add((short)30000);
        numberList.add(1234567890);
        numberList.add(1234567890L);

        numberList.add((long)2e62);
        numberList.add((float)3.4);
        numberList.add(4000.8);
        numberList.add(new BigInteger("23423553453453453453"));
        numberList.add(new BigDecimal("2.1e309"));

        for (Number n : numberList){
            System.out.println(n + "\t".repeat(2) +  n.getClass().getName());
        }
    }
}
