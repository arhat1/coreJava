package com.test.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static java.lang.System.out;

public class GroupingByTest {
    public static void main(String[] args) {
       Locale[] locales = Locale.getAvailableLocales();
        Map<String, List<Locale>> countryToLocales = Arrays.stream(locales).collect(Collectors.groupingBy(Locale::getCountry));
        List<Locale> swissLocales = countryToLocales.get("CH");
        out.println(swissLocales);
    }
}
