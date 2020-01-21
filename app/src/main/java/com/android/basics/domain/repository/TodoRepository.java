package com.android.basics.domain.repository;

import com.android.basics.core.Callback;
import com.android.basics.domain.model.Todo;

import java.util.List;

public interface TodoRepository {
    void getTodoList(int userId, Callback<List<Todo>> callback);

    void getTodo(int todoId, Callback<Todo> callback);

    void editTodo(Todo todo);

    void deleteTodo(int todoId);
}
