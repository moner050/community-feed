package org.lmh.common;

import java.time.LocalDate;

public class TimeCalculator {

    private TimeCalculator() {}

    public static LocalDate getDateDaysAgo(final int daysAgo) {
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(daysAgo);
    }
}
