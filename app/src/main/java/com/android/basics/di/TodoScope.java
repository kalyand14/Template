package com.android.basics.di;

import com.android.basics.core.di.BaseScope;
import com.android.basics.core.di.InstanceContainer;
import com.android.basics.core.di.InstanceScope;

public class TodoScope implements BaseScope {
    private final InstanceContainer container = new InstanceContainer();
    private static TodoScope instance = null;

    private TodoScope() {
    }

    public static TodoScope getInstance() {
        if (instance == null) {
            instance = new TodoScope();
        }
        return instance;
    }

    @Override
    public void end() {
        container.end();
    }
}
