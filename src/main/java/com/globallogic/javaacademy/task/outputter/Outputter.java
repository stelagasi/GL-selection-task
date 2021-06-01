package com.globallogic.javaacademy.task.outputter;

import com.globallogic.javaacademy.task.calculator.CalculationResult;

@FunctionalInterface
public interface Outputter {

    void output(CalculationResult result);
}
