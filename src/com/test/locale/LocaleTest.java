package com.test.locale;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import static java.lang.System.out;
public class LocaleTest {
    public static void main(String[] args) throws ParseException {
        System.out.println(Locale.US.toLanguageTag());
        Locale locale = new Locale("zh", "TW");
        out.println(locale.getDisplayName());
        out.println(locale.getDisplayName(Locale.TRADITIONAL_CHINESE));
        out.println(locale.getDisplayName(Locale.US));
        out.println("_________________________________________________________");
        Locale loc = Locale.getDefault();
        //Locale loc = new Locale("zh", "TW");
        out.printf("%s: %s\n", "getLanguage", loc.getLanguage());
        out.printf("%s: %s\n", "getDisplayLanguage", loc.getDisplayLanguage());
        out.printf("%s: %s\n", "getCountry", loc.getCountry());
        out.printf("%s: %s\n", "getDisplayCountry", loc.getDisplayCountry());
        out.printf("%s: %s\n", "getDisplayName", loc.getDisplayName());
        out.printf("%s: %s\n", "toLanguageTag",loc.toLanguageTag());
        out.printf("%s: %s\n", "getDisplayName(Locale.US)" ,loc.getDisplayName(Locale.US));
        out.printf("%s: %s\n", "getDisplayLanguage(Locale.US)" ,loc.getDisplayLanguage(Locale.US) );
        out.printf("%s: %s\n", "getDisplayCountry(Locale.US)" ,loc.getDisplayCountry(Locale.US) );
        out.printf("%s: %s\n", "toString" ,loc.toString() );
        out.println("_________________________________________________________");

        Locale gerloc = Locale.GERMAN;
        NumberFormat currFmt = NumberFormat.getCurrencyInstance(gerloc);
        //NumberFormat currFmt = NumberFormat.getCurrencyInstance();
        double amt = 123456.78;
        String result = currFmt.format(amt);
        out.println(result);
        out.println(currFmt.parse(result));

    }
}
