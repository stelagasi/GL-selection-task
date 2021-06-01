package com.globallogic.javaacademy.task.calculator;

import java.util.List;

public record CalculationResult(List<OccurrenceRecord> records, int wordsLength, int wantedCharsLength) {
}
