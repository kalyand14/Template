package com.android.basics.domain.model;

public class Todo {
    private int todoId;
    private String name;
    private String description;
    private String date;

    public Todo(int todoId, String name, String description, String date) {
        this.todoId = todoId;
        this.name = name;
        this.description = description;
        this.date = date;
    }
}
