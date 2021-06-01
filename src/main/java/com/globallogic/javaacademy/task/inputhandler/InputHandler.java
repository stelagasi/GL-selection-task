package com.globallogic.javaacademy.task.inputhandler;

import java.util.List;

@FunctionalInterface
public interface InputHandler {

    void handle(String line, List<String> words);
}
