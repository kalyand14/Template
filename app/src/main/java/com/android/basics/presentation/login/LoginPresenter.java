package com.android.basics.presentation.login;

import com.android.basics.core.Callback;
import com.android.basics.domain.interactor.AuthenticateUser;
import com.android.basics.domain.model.User;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.Navigator navigator;

    private AuthenticateUser authenticateUser;

    private LoginContract.View view;

    public LoginPresenter(LoginContract.Navigator navigator, AuthenticateUser authenticateUser) {
        this.navigator = navigator;
        this.authenticateUser = authenticateUser;
    }

    @Override
    public void OnLoginClick(String userName, String password) {
        view.showProgressDialog();

        authenticateUser.execute(AuthenticateUser.Params.forUser(userName, password), new Callback<User>() {
            @Override
            public void onResponse(User response) {

                view.dismissProgressDialog();
                if (response.getUserId() != 0) {
                    navigator.goToHomeScreen();
                } else {
                    view.showAuthenticationError();
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                view.dismissProgressDialog();
                view.showAuthenticationError();
            }
        });


    }

    @Override
    public void onRegisterClick() {
        navigator.goToRegisterScreen();
    }

    @Override
    public void attach(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
