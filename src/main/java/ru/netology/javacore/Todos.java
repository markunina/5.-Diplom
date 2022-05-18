package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    List<String> todos = new ArrayList<>();

    public void addTask(String task) {
        todos.add(task);
    }

    public void removeTask(String task) {
        todos.remove(task);
    }

    public String getAllTasks() {
        todos.sort((p1, p2) -> p1.compareTo(p2));
        StringBuilder sB = new StringBuilder();
        for(var str : todos) {
            sB.append(str).append(" ");
        }
        return sB.toString();
    }

}
