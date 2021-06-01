package com.globallogic.javaacademy.task.outputter;

import com.globallogic.javaacademy.task.calculator.CalculationResult;
import com.globallogic.javaacademy.task.calculator.OccurrenceRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class FileOutputter implements Outputter {

    private final StringBuilder stringBuilder = new StringBuilder();
    private final Path path;

    public FileOutputter(Path path) {
        requireNonNull(path);
        this.path = path;
    }

    @Override
    public void output(CalculationResult result) {
        requireNonNull(result);

        stringBuilder.setLength(0);

        for (OccurrenceRecord occurrenceRecord : result.records()) {
            stringBuilder.append("{%s, %d} = %.2f (%d/%d)%n".formatted(
                    occurrenceRecord.chars().stream().map(Object::toString).collect(joining(", ", "(", ")")),
                    occurrenceRecord.length(),
                    (double) occurrenceRecord.count() / result.wantedCharsLength(),
                    occurrenceRecord.count(),
                    result.wantedCharsLength()));
        }

        stringBuilder.append("TOTAL Frequency: %.2f (%d/%d)%n".formatted(
                (double) result.wantedCharsLength() / result.wordsLength(),
                result.wantedCharsLength(),
                result.wordsLength()));

        try {
            Files.writeString(path, stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
