package com.globallogic.javaacademy.task.inputhandler;

import java.util.List;
import java.util.Set;

import static java.util.Locale.ROOT;
import static java.util.Objects.requireNonNull;

public class StringParser implements InputHandler {

    private static final Set<Character> SEPARATORS = Set.of('\n', '\t', ' ');

    private final StringBuilder builder = new StringBuilder();
    private final Set<Character> specialChars;

    public StringParser(Set<Character> specialChars) {
        requireNonNull(specialChars);
        this.specialChars = specialChars;
    }

    @Override
    public void handle(String line, List<String> words) {
        requireNonNull(line);
        requireNonNull(words);

        builder.setLength(0);

        for (char c : line.toCharArray()) {
            if (SEPARATORS.contains(c) && !builder.isEmpty()) {
                words.add(builder.toString().toLowerCase(ROOT));
                builder.setLength(0);
            } else if (!specialChars.contains(c)) {
                builder.append(c);
            }
        }

        if (!builder.isEmpty()) {
            words.add(builder.toString().toLowerCase(ROOT));
            builder.setLength(0);
        }
    }
}
