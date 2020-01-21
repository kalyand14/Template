package com.android.basics.data.repository;

import com.android.basics.core.Callback;
import com.android.basics.data.core.DaoCallback;
import com.android.basics.data.core.DaoExecutor;
import com.android.basics.data.mapper.TodoListMapper;
import com.android.basics.data.mapper.TodoMapper;
import com.android.basics.data.source.dao.TodoDao;
import com.android.basics.domain.model.Todo;
import com.android.basics.domain.repository.TodoRepository;

import java.util.List;

public class TodoDataRespository implements TodoRepository {

    private TodoDao todoDao;
    private DaoExecutor daoExecutor;
    private TodoListMapper todoListMapper;
    private TodoMapper todoMapper;

    public TodoDataRespository(TodoDao todoDao, DaoExecutor daoExecutor, TodoListMapper todoListMapper, TodoMapper todoMapper) {
        this.todoDao = todoDao;
        this.daoExecutor = daoExecutor;
        this.todoListMapper = todoListMapper;
        this.todoMapper = todoMapper;
    }

    @Override
    public void getTodoList(int userId, Callback<List<Todo>> callback) {
        DaoCallback daoCallback = () -> {
            List<Todo> response = todoListMapper.convert(todoDao.getAllTodo(userId));
            if (response != null) {
                callback.onResponse(response);
            } else {
                callback.onError("00002", "No data available");
            }
        };
        daoExecutor.start(daoCallback);
    }

    @Override
    public void getTodo(int todoId, Callback<Todo> callback) {
        DaoCallback daoCallback = () -> {
            Todo response = todoMapper.convert(todoDao.getTodo(todoId));
            if (response != null) {
                callback.onResponse(response);
            } else {
                callback.onError("00002", "No data available");
            }
        };
        daoExecutor.start(daoCallback);
    }

    @Override
    public void editTodo(Todo todo) {
        DaoCallback daoCallback = () -> {
            todoDao.update(todoMapper.invert(todo));
        };
        daoExecutor.start(daoCallback);
    }

    @Override
    public void deleteTodo(int todoId) {
        DaoCallback daoCallback = () -> {
            todoDao.delete(todoId);
        };
        daoExecutor.start(daoCallback);
    }
}
