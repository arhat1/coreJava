package com.test.datetime;

import java.time.*;

import static java.lang.System.out;
public class ZonedTimes {
    public static void main(String[] args) {
        ZonedDateTime apollo11lanuch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
        out.println("apollo11launch: " + apollo11lanuch);

        Instant instant = apollo11lanuch.toInstant();
        out.println("instant: " + instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        out.println("zonedDateTime: " + zonedDateTime);

        ZonedDateTime skipped  = ZonedDateTime.of(LocalDate.of(2013, 3, 31), LocalTime.of(2, 30),
                ZoneId.of("Europe/Berlin"));
        out.println("skipped: " + skipped);

        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        ZonedDateTime anHourLater = ambiguous.plusHours(1);
        out.println("ambiguous: " + ambiguous);
        out.println("anHourLater: " + anHourLater);

        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31),
                LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
        out.println("meeting: " + meeting);

        ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
        out.println("nextMeeting: " + nextMeeting);

        nextMeeting = meeting.plus(Period.ofDays(7));
        out.println("nextMeeting: " + nextMeeting);
    }
}
