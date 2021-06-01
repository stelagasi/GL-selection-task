package com.globallogic.javaacademy.task.calculator;

import java.util.*;

import static java.util.Objects.*;
import static java.util.stream.Collectors.toList;

public record FrequencyCalculator(Set<Character> wantedChars) {

    public CalculationResult calculate(List<String> words) {
        requireNonNull(words);

        Map<Integer, Map<Set<Character>, Integer>> mapByLength = new HashMap<>();
        int wordsLength = 0, wantedCharsLength = 0;

        for (String word : words) {
            int wordLength = word.length();
            wordsLength += wordLength;

            if (!mapByLength.containsKey(wordLength)) {
                mapByLength.put(wordLength, new HashMap<>());
            }
            Map<Set<Character>, Integer> mapByWantedChars = mapByLength.get(wordLength);

            Set<Character> wantedCharsInWord = new LinkedHashSet<>();
            int totalWantedCharsCounter = getTotalWantedCharsCounter(word, wantedCharsInWord);

            wantedCharsLength += totalWantedCharsCounter;

            int previousValue = mapByWantedChars.getOrDefault(wantedCharsInWord, 0);
            mapByWantedChars.put(wantedCharsInWord, previousValue + totalWantedCharsCounter);
        }

        return new CalculationResult(mapToOccurrenceRecords(mapByLength), wordsLength, wantedCharsLength);
    }

    private List<OccurrenceRecord> mapToOccurrenceRecords(Map<Integer, Map<Set<Character>, Integer>> mapByLength) {
        return mapByLength.entrySet().stream()
                .map(outer -> outer.getValue().entrySet().stream()
                        .map(inner -> new OccurrenceRecord(outer.getKey(), inner.getKey(), inner.getValue()))
                        .collect(toList()))
                .flatMap(List::stream)
                .collect(toList());
    }

    private int getTotalWantedCharsCounter(String word, Set<Character> wantedCharsInWord) {
        int totalWantedCharsCounter = 0;
        for (char c : word.toCharArray()) {
            if (wantedChars.contains(c)) {
                wantedCharsInWord.add(c);
                totalWantedCharsCounter++;
            }
        }
        return totalWantedCharsCounter;
    }
}