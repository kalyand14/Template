package com.android.basics.domain.interactor;

import com.android.basics.core.Callback;
import com.android.basics.core.mvp.UseCase;
import com.android.basics.domain.model.Todo;
import com.android.basics.domain.repository.TodoRepository;

public class EditTodo extends UseCase<Todo, Boolean> {

    private TodoRepository todoRepository;

    public EditTodo(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    protected void executeTask(Todo param, final Callback<Boolean> callback) {
        todoRepository.editTodo(param, new Callback<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                if (!isDisposed()) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                if (!isDisposed()) {
                    callback.onError("E4", "Could not able to edit todo!!!");
                }
            }
        });
    }
}
