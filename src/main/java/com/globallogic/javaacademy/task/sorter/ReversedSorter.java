package com.globallogic.javaacademy.task.sorter;

import com.globallogic.javaacademy.task.calculator.CalculationResult;
import com.globallogic.javaacademy.task.calculator.OccurrenceRecord;

import java.util.Comparator;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static java.util.Objects.requireNonNull;

public class ReversedSorter implements Sorter {

    private static final Comparator<OccurrenceRecord> COMPARATOR = comparingInt(OccurrenceRecord::count)
            .thenComparing(OccurrenceRecord::length)
            .thenComparing(OccurrenceRecord::chars, comparingInt(Set::size)).reversed();

    @Override
    public void sort(CalculationResult result) {
        requireNonNull(result);

        result.records().sort(COMPARATOR);
    }
}
