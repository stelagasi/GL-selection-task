package com.globallogic.javaacademy.task.sorter;

import com.globallogic.javaacademy.task.calculator.CalculationResult;

@FunctionalInterface
public interface Sorter {

    void sort(CalculationResult result);
}
