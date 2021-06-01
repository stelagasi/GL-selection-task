package com.globallogic.javaacademy.task;

import com.globallogic.javaacademy.task.calculator.CalculationResult;
import com.globallogic.javaacademy.task.calculator.FrequencyCalculator;
import com.globallogic.javaacademy.task.inputhandler.InputHandler;
import com.globallogic.javaacademy.task.inputhandler.StringParser;
import com.globallogic.javaacademy.task.outputter.FileOutputter;
import com.globallogic.javaacademy.task.outputter.Outputter;
import com.globallogic.javaacademy.task.sorter.DefaultSorter;
import com.globallogic.javaacademy.task.sorter.Sorter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    public static final Set<Character> CHARS_TO_CALCULATE = Set.of('l', 'o', 'g', 'i', 'c');
    public static final Set<Character> SPECIAL_CHARS = Set.of('!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', ' ');
    public static final Path OUTPUT_PATH = Path.of("src/main/resources/out.txt");

    public static final InputHandler stringParser = new StringParser(SPECIAL_CHARS);
    public static final FrequencyCalculator calculator = new FrequencyCalculator(CHARS_TO_CALCULATE);
    public static final Outputter fileOutputter = new FileOutputter(OUTPUT_PATH);
    public static final Sorter sorter = new DefaultSorter();

    @Test
    void givenTestSample() throws IOException {
        List<String> words = new ArrayList<>();
        stringParser.handle("I love to work in global logic!", words);
        CalculationResult result = calculator.calculate(words);
        sorter.sort(result);
        fileOutputter.output(result);

        assertEquals(List.of("{(i), 1} = 0,07 (1/15)",
                "{(i), 2} = 0,07 (1/15)",
                "{(o), 2} = 0,07 (1/15)",
                "{(o), 4} = 0,07 (1/15)",
                "{(l, o), 4} = 0,13 (2/15)",
                "{(g, l, o), 6} = 0,27 (4/15)",
                "{(l, o, g, i, c), 5} = 0,33 (5/15)",
                "TOTAL Frequency: 0,63 (15/24)"), Files.readAllLines(OUTPUT_PATH));
    }
}
