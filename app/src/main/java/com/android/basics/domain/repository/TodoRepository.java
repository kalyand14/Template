package com.android.basics.domain.repository;

import com.android.basics.core.Callback;
import com.android.basics.domain.model.Todo;
import com.android.basics.domain.model.User;

import java.util.List;

public interface TodoRepository {
    void getTodoList(User User, Callback<List<Todo>> callback);

    void editTodo(Todo todo, Callback<Boolean> callback);
}
