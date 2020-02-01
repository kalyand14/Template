package com.android.basics.domain.interactor;

import com.android.basics.core.Callback;
import com.android.basics.core.mvp.UseCase;
import com.android.basics.domain.repository.TodoRepository;

public class AddTodo extends UseCase<AddTodo.Params, Boolean> {

    private TodoRepository todoRepository;

    public AddTodo(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    protected void executeTask(AddTodo.Params param, final Callback<Boolean> callback) {
        todoRepository.addTodo(param.userId, param.name, param.description, param.date, new Callback<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                if (!isDisposed()) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                if (!isDisposed()) {
                    callback.onError(errorcode, errorResponse);
                }
            }
        });
    }

    public static final class Params {
        private int userId;
        private String name;
        private String description;
        private String date;

        private Params(int userId, String name, String description, String date) {
            this.userId = userId;
            this.name = name;
            this.description = description;
            this.date = date;
        }

        public static AddTodo.Params forTodo(int userId, String name, String description, String date) {
            return new AddTodo.Params(userId, name, description, date);
        }
    }
}
