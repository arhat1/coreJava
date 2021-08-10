package com.corejava.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static java.lang.System.out;
public class ZonedTimes {
    public static void main(String[] args) {
        ZonedDateTime apoll011Launch = ZonedDateTime.of(1969,7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
        out.println("apollo11Launch: " + apoll011Launch);

        Instant instant = apoll011Launch.toInstant();
        out.println("instant: " + instant);
        out.println(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apoll011Launch));

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        out.println("zoneDateTime: "+ zonedDateTime);

        ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        out.println("skipped: " + skipped);

        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        ZonedDateTime anHourLater = ambiguous.plusHours(1);
        out.println("ambigous: " + ambiguous);
        out.println("anHourLater: " + anHourLater);

        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31),
                LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
        out.println("meeting: " + meeting);
        ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
        out.println("nextMeeting: " + nextMeeting);

        nextMeeting = meeting.plus(Period.ofDays(7));
        out.println("netxMeeting: " + nextMeeting);



    }
}
