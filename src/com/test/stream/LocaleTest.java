package com.test.stream;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.lang.System.out;

public class LocaleTest {
    public static void main(String[] args) {
        Stream.of(Locale.getAvailableLocales()).filter(x->!x.getCountry().equals("")).forEach(x->out.printf("%s\t%s\t%s\t%s\t%s\n", x.getDisplayName(),
                x.getCountry(), x.getDisplayCountry(), x.getLanguage(), x.getDisplayLanguage()));
        out.println("___________________________________");

    }
}
