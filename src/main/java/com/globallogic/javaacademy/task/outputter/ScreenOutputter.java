package com.globallogic.javaacademy.task.outputter;

import com.globallogic.javaacademy.task.calculator.CalculationResult;
import com.globallogic.javaacademy.task.calculator.OccurrenceRecord;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class ScreenOutputter implements Outputter {

    @Override
    public void output(CalculationResult result) {
        requireNonNull(result);

        for (OccurrenceRecord occurrenceRecord : result.records()) {
            System.out.printf("{%s, %d} = %.2f (%d/%d)%n",
                    occurrenceRecord.chars().stream().map(Object::toString).collect(joining(", ", "(", ")")),
                    occurrenceRecord.length(),
                    (double) occurrenceRecord.count() / result.wantedCharsLength(),
                    occurrenceRecord.count(),
                    result.wantedCharsLength());
        }

        System.out.printf("TOTAL Frequency: %.2f (%d/%d)%n",
                (double) result.wantedCharsLength() / result.wordsLength(),
                result.wantedCharsLength(),
                result.wordsLength());
    }
}
