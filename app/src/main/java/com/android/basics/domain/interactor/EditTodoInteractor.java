package com.android.basics.domain.interactor;

import com.android.basics.core.Callback;
import com.android.basics.core.mvp.UseCase;
import com.android.basics.domain.model.Todo;
import com.android.basics.domain.repository.TodoRepository;

public class EditTodoInteractor extends UseCase<Todo, Boolean> {

    private TodoRepository todoRepository;

    public EditTodoInteractor(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    protected void executeTask(Todo param, final Callback<Boolean> callback) {
        todoRepository.editTodo(param);
    }
}
