package com.globallogic.javaacademy.task.inputhandler;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StringParserTest {

    private final StringParser stringParser = new StringParser(Set.of(' ', '.', ','));

    @Test
    void handle_whenLineIsNull_thenExceptionIsThrown() {
        assertThrows(NullPointerException.class, () -> stringParser.handle(null, List.of()));
    }

    @Test
    void handle_whenWordsListIsNull_thenExceptionIsThrown() {
        assertThrows(NullPointerException.class, () -> stringParser.handle("a", null));
    }

    @Test
    void handle_whenAllOk_thenWordsDontContainSpecialChars() {
        String line = ".,   ab.  cd";
        List<String> words = new ArrayList<>();

        stringParser.handle(line, words);

        assertEquals(List.of("ab", "cd"), words);
        assertTrue(words.stream().noneMatch(e -> e.contains(" ")));
        assertTrue(words.stream().noneMatch(e -> e.contains(".")));
        assertTrue(words.stream().noneMatch(e -> e.contains(",")));
    }
}