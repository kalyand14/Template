package com.android.basics.presentation.home;

import com.android.basics.core.Callback;
import com.android.basics.domain.interactor.GetTodoList;
import com.android.basics.domain.model.Todo;
import com.android.basics.presentation.components.UserSession;

import java.util.List;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private GetTodoList getTodoList;

    private HomeScreenContract.View view;

    private HomeScreenContract.Navigator navigator;

    private UserSession session;

    public HomeScreenPresenter(GetTodoList getTodoList, UserSession session, HomeScreenContract.Navigator navigator) {
        this.getTodoList = getTodoList;
        this.navigator = navigator;
        this.session = session;
    }

    @Override
    public void onLoadTodoList(int userId) {

        view.setWelcomeMessage("Welcome " + session.getUser().getUserName());

        view.showProgressDialog();
        getTodoList.execute(GetTodoList.Params.forUser(userId), new Callback<List<Todo>>() {
            @Override
            public void onResponse(List<Todo> response) {

                if (response != null && response.size() > 0) {
                    session.setTodoList(response);
                    view.showList(true);
                    view.showErrorLayout(false);
                    view.loadTodoList(response);
                    view.dismissProgressDialog();
                } else {
                    showError();
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                showError();
            }
        });
    }

    private void showError() {
        view.showList(false);
        view.showErrorLayout(true);
        view.dismissProgressDialog();
    }

    @Override
    public void onLogout() {
        navigator.goToLoginScreen();
    }

    @Override
    public void onAddTodo() {
        navigator.gotoAddTodoScreen();
    }

    @Override
    public void attach(HomeScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }
}
