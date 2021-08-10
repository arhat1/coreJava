package com.corejava.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

import static java.lang.System.out;

public class DateFormatting {
    public static void main(String[] args) {
        ZonedDateTime apollo11Launch = ZonedDateTime.of(1969, 7, 16, 9, 32,0, 0,
                ZoneId.of("America/New_York"));
        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11Launch);
        out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(apollo11Launch);
        out.println(formatted);

        formatted = formatter.withLocale(Locale.CHINA).format(apollo11Launch);
        out.println(formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(apollo11Launch);
        out.println(formatted);

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        out.println("churchsBirthday: " +  churchsBirthday);
        apollo11Launch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        out.println("apollo11Lanuch: " + apollo11Launch);

        for (DayOfWeek w: DayOfWeek.values()){
            out.print(w.getDisplayName(TextStyle.FULL, Locale.CHINESE));
        }


    }
}
