package com.android.basics.di;

import com.android.basics.core.di.BaseScope;
import com.android.basics.core.di.InstanceContainer;

public class UserScope implements BaseScope {
    private final InstanceContainer container = new InstanceContainer();
    private static UserScope instance = null;

    private TodoScope todoScope;

    private UserScope() {

    }

    public static UserScope getInstance() {
        if (instance == null) {
            instance = new UserScope();
        }
        return instance;
    }

    @Override
    public void end() {
        todoScope.end();
        container.end();
    }

    public TodoScope getTodoScope() {
        return todoScope;
    }

    public void setTodoScope(TodoScope todoScope) {
        this.todoScope = todoScope;
    }
}
