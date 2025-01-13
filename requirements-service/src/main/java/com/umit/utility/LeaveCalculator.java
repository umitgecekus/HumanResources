package com.umit.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LeaveCalculator {

    public Double calculateNumberOfDays (LocalDate startDate, LocalDate endDate) {
        long duration = endDate.toEpochDay() - startDate.toEpochDay();
        return (double) duration;
    }

}
