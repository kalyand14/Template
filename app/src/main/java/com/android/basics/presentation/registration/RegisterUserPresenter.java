package com.android.basics.presentation.registration;

import com.android.basics.core.Callback;
import com.android.basics.domain.interactor.RegisterUser;
import com.android.basics.domain.model.User;

public class RegisterUserPresenter implements RegisterUserContract.Presenter {

    private RegisterUser registerUser;

    private RegisterUserContract.View view;

    private RegisterUserContract.Navigator navigator;

    public RegisterUserPresenter(RegisterUserContract.Navigator navigator, RegisterUser registerUser) {
        this.registerUser = registerUser;
        this.navigator = navigator;
    }

    @Override
    public void onRegisterClick(String userName, String password) {

        view.showProgressDialog();

        registerUser.execute(RegisterUser.Params.forUser(userName, password), new Callback<User>() {
            @Override
            public void onResponse(User response) {
                view.dismissProgressDialog();
                view.showRegistrationSuccess();
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                view.dismissProgressDialog();
                view.showRegistrationError();
            }
        });

    }

    @Override
    public void onLoginClick() {
        navigator.goToLoginScreen();
    }

    @Override
    public void onRegistrationSuccess() {
        navigator.goToHomeScreen();
    }

    @Override
    public void attach(RegisterUserContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
