package com.example.springclienteprojeto.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public final class DateConverter {

    private DateConverter() {
    }

    public static int convertYear(final String year) {
        if (year.matches("\\d+")) {
            return Integer.parseInt(year);
        }
        return Arrays.stream(year.split("\\D"))
                .map(Integer::parseInt)
                .findFirst()
                .orElseThrow();
    }

    public static LocalDate convertDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
