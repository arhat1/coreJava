package com.corejava.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;

public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        out.println("today : " + today);

        LocalDate alonzosBirthday = LocalDate.of(1903, 6,14);
        out.println("alonzosBirthday: " + alonzosBirthday);

        LocalDate programersDay = LocalDate.of(2021,1,1)
                .plusDays(255);
        out.println("programersDay: " + programersDay);

        LocalDate nationalDay = LocalDate.of(2021, Month.OCTOBER, 1);
        LocalDate christmas = LocalDate.of(2021, Month.DECEMBER, 25);
        out.println("Until christmas: " + nationalDay.until(christmas));
        out.println("Until christmas: " + nationalDay.until(christmas, ChronoUnit.DAYS));

        out.println(LocalDate.of(2015, 1, 31).plusMonths(1));
        out.println(LocalDate.of(2015, 3, 31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1).getDayOfWeek();
        out.println("startOfLastMillennium: " + startOfLastMillennium);
        out.println(startOfLastMillennium.getValue());

        out.println(DayOfWeek.SATURDAY.plus(3));
    }
}
