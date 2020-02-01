package com.android.basics.presentation.todo.add;

import com.android.basics.core.Callback;
import com.android.basics.domain.interactor.AddTodo;
import com.android.basics.presentation.components.UserSession;

public class AddTodoPresenter implements AddTodoContract.Presenter {

    private AddTodoContract.View view;

    private AddTodoContract.Navigator navigator;

    private AddTodo addTodo;

    private UserSession session;

    public AddTodoPresenter(AddTodoContract.Navigator navigator, AddTodo addTodo, UserSession session) {
        this.navigator = navigator;
        this.addTodo = addTodo;
        this.session = session;
    }

    @Override
    public void onSubmit(String name, String desc, String date) {
        addTodo.execute(AddTodo.Params.forTodo(session.getUser().getUserId(), name, desc, date), new Callback<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                view.dismissProgressDialog();
                if (response) {
                    view.showSuccessDialog();
                } else {
                    view.showErrorDialog();
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                view.dismissProgressDialog();
                view.showErrorDialog();
            }
        });
    }

    @Override
    public void navigate() {
        navigator.goToHomeScreen();
    }

    @Override
    public void OnCancel() {
        navigator.goToHomeScreen();
    }

    @Override
    public void attach(AddTodoContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }
}
