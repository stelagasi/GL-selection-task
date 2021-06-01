package com.globallogic.javaacademy.task.sorter;

import com.globallogic.javaacademy.task.calculator.CalculationResult;
import com.globallogic.javaacademy.task.calculator.OccurrenceRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReversedSorterTest {

    private final ReversedSorter reversedSorter = new ReversedSorter();

    @Test
    void sort_whenResultNull_thenExceptionIsThrown() {
        assertThrows(NullPointerException.class, () -> reversedSorter.sort(null));
    }

    @Test
    void sort_whenAllOk_resultIsSorted() {
        OccurrenceRecord or1 = new OccurrenceRecord(2, Set.of('a', 'b'), 5);
        OccurrenceRecord or2 = new OccurrenceRecord(2, Set.of('c', 'd'), 4);
        OccurrenceRecord or3 = new OccurrenceRecord(3, Set.of('a', 'b', 'c'), 3);
        OccurrenceRecord or4 = new OccurrenceRecord(3, Set.of('a', 'b'), 3);//
        OccurrenceRecord or5 = new OccurrenceRecord(4, Set.of('a', 'b'), 3);
        List<OccurrenceRecord> ors = new ArrayList<>(List.of(or1, or2, or3, or4, or5));

        CalculationResult calculationResult = new CalculationResult(ors, 24, 18);
        reversedSorter.sort(calculationResult);

        assertEquals(5, calculationResult.records().size());
        assertEquals(4, calculationResult.records().indexOf(or4));
        assertEquals(3, calculationResult.records().indexOf(or3));
        assertEquals(2, calculationResult.records().indexOf(or5));
        assertEquals(1, calculationResult.records().indexOf(or2));
        assertEquals(0, calculationResult.records().indexOf(or1));
    }

}