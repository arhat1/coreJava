package com.test.locale;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import  static  java.lang.System.out;

public class CurrencyTest {
    public static void main(String[] args) {
       Set<Currency> currencies =  Currency.getAvailableCurrencies();
       currencies.stream().forEach(x -> {
           out.printf("%s: %s\t","getCurrencyCode", x.getCurrencyCode());
           out.printf("%s: %s\t", "getNumericCode", x.getNumericCode() );
           out.printf("%s: %s\t", "getSymbol", x.getSymbol());
           out.printf("%s: %s\t", "getDefaultFractionDigits", x.getDefaultFractionDigits() );
           out.printf("%s: %s\n", "toString", x.toString());
       });

       Currency currency = Currency.getInstance(Locale.FRANCE);
       out.println(currency.getSymbol());

        NumberFormat euroFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        euroFormatter.setCurrency(Currency.getInstance("EUR"));
        out.println(euroFormatter.format(1234.56));
    }
}
