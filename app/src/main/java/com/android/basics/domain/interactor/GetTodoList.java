package com.android.basics.domain.interactor;

import com.android.basics.core.Callback;
import com.android.basics.core.mvp.UseCase;
import com.android.basics.domain.model.Todo;
import com.android.basics.domain.model.User;
import com.android.basics.domain.repository.TodoRepository;

import java.util.List;

public class GetTodoList extends UseCase<User, List<Todo>> {

    private TodoRepository todoRepository;

    public GetTodoList(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    protected void executeTask(User param, final Callback<List<Todo>> callback) {
        todoRepository.getTodoList(param, new Callback<List<Todo>>() {
            @Override
            public void onResponse(List<Todo> response) {
                if (!isDisposed()) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                if (!isDisposed()) {
                    callback.onError("E3", "Could not able to retrieve todo list!!!");
                }
            }
        });
    }
}
