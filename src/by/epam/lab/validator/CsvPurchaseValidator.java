package by.epam.lab.validator;

import by.epam.lab.entity.Byn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CsvPurchaseValidator {
    private static final Pattern CSV_PATTERN = Pattern.compile("[A-Za-zА-Яа-я]+;\\d+;\\d+(;\\d+)?");

    private CsvPurchaseValidator() {
    }

    public static boolean isCsvValid(String csv) {
        Matcher csvMatcher = CSV_PATTERN.matcher(csv);
        return csvMatcher.matches();
    }

    public static boolean isCostValid(int cost) {
        return cost == 0;
    }

    public static boolean isDiscountValid(int discount) {
        return discount == 0;
    }
}