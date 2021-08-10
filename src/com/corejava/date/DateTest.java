package com.corejava.date;
import static java.lang.System.out;
import java.time.Instant;

public class DateTest {
    public static void main(String[] args) {
        Instant now = Instant.now();
        out.println(now);
        out.println(Instant.MAX);
        out.println(Instant.MIN);
    }
}
