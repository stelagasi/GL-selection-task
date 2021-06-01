package com.globallogic.javaacademy.task.outputter;

import com.globallogic.javaacademy.task.calculator.CalculationResult;
import com.globallogic.javaacademy.task.calculator.OccurrenceRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileOutputterTest {

    private final static Path PATH = Path.of("./out.txt");
    private final FileOutputter fileOutputter = new FileOutputter(PATH);

    @Test
    void output_whenResultIsNull_thenThrowException() {
        assertThrows(NullPointerException.class, () -> fileOutputter.output(null));
    }

    @Test
    void output_whenAllIsOk_thenOutputIsInFile() throws IOException {
        OccurrenceRecord or1 = new OccurrenceRecord(2, new LinkedHashSet<>(List.of('a', 'b')), 5);
        OccurrenceRecord or2 = new OccurrenceRecord(2, new LinkedHashSet<>(List.of('c', 'd')), 4);
        OccurrenceRecord or3 = new OccurrenceRecord(3, new LinkedHashSet<>(List.of('a', 'b', 'c')), 3);
        OccurrenceRecord or4 = new OccurrenceRecord(3, new LinkedHashSet<>(List.of('a', 'b')), 3);
        OccurrenceRecord or5 = new OccurrenceRecord(4, new LinkedHashSet<>(List.of('a', 'b')), 3);
        List<OccurrenceRecord> ors = new ArrayList<>(List.of(or1, or2, or3, or4, or5));
        CalculationResult calculationResult = new CalculationResult(ors, 24, 18);

        fileOutputter.output(calculationResult);

        assertEquals(List.of(
                "{(a, b), 2} = 0,28 (5/18)",
                "{(c, d), 2} = 0,22 (4/18)",
                "{(a, b, c), 3} = 0,17 (3/18)",
                "{(a, b), 3} = 0,17 (3/18)",
                "{(a, b), 4} = 0,17 (3/18)",
                "TOTAL Frequency: 0,75 (18/24)"),
                Files.readAllLines(PATH));
    }
}