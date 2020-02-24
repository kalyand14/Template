package com.android.basics.data.source.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo")
public class TodoTbl {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int todoId;
    private int userId;
    private String name;
    private String description;
    private String dueDate;
    private Boolean isCompleted;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public int getTodoId() {
        return todoId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public TodoTbl(int userId, String name, String description, String dueDate, boolean isCompleted) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }
}
