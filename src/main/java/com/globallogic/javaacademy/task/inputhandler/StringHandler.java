package com.globallogic.javaacademy.task.inputhandler;

import java.util.List;
import java.util.Set;

import static java.util.Locale.ROOT;
import static java.util.Objects.requireNonNull;

public class StringHandler implements InputHandler {

    private final Set<Character> specialChars;
    private final StringBuilder builder = new StringBuilder();

    public StringHandler(Set<Character> specialChars) {
        requireNonNull(specialChars);
        this.specialChars = specialChars;
    }

    @Override
    public void handle(String line, List<String> words) {
        requireNonNull(line);
        requireNonNull(words);

        builder.setLength(0);
        String[] separatedLine = line.split("\\s+");

        for (String word : separatedLine) {
            for (char c : word.toCharArray()) {
                if (!specialChars.contains(c)) {
                    builder.append(c);
                }
            }
            if (!builder.isEmpty()) {
                words.add(builder.toString().toLowerCase(ROOT));
                builder.setLength(0);
            }
        }
    }
}