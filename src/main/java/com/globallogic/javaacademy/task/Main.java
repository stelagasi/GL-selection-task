package com.globallogic.javaacademy.task;

import com.globallogic.javaacademy.task.calculator.CalculationResult;
import com.globallogic.javaacademy.task.calculator.FrequencyCalculator;
import com.globallogic.javaacademy.task.inputhandler.InputHandler;
import com.globallogic.javaacademy.task.inputhandler.StringParser;
import com.globallogic.javaacademy.task.outputter.FileOutputter;
import com.globallogic.javaacademy.task.outputter.Outputter;
import com.globallogic.javaacademy.task.outputter.ScreenOutputter;
import com.globallogic.javaacademy.task.sorter.DefaultSorter;
import com.globallogic.javaacademy.task.sorter.Sorter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static final Set<Character> CHARS_TO_CALCULATE = Set.of('l', 'o', 'g', 'i', 'c');
    public static final Set<Character> SPECIAL_CHARS = Set.of('!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', ' ');
    public static final Path OUTPUT_PATH = Path.of("src/main/resources/out.txt");

    //    public static final InputHandler stringHandler = new StringHandler(SPECIAL_CHARS);
    public static final InputHandler stringParser = new StringParser(SPECIAL_CHARS);
    public static final FrequencyCalculator calculator = new FrequencyCalculator(CHARS_TO_CALCULATE);
    public static final Outputter screenOutputter = new ScreenOutputter();
    public static final Outputter fileOutputter = new FileOutputter(OUTPUT_PATH);
    public static final Sorter sorter = new DefaultSorter();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();

        while (scanner.hasNextLine()) {
            stringParser.handle(scanner.nextLine(), words);
        }

        CalculationResult result = calculator.calculate(words);
        sorter.sort(result);

        fileOutputter.output(result);
        screenOutputter.output(result);
        scanner.close();
    }
}
