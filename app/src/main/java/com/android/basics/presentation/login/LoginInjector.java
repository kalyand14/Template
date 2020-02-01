package com.android.basics.presentation.login;

import android.app.ProgressDialog;

import com.android.basics.core.TodoApplication;
import com.android.basics.di.ApplicationScope;
import com.android.basics.domain.interactor.AuthenticateUser;
import com.android.basics.presentation.TodoNavigator;
import com.android.basics.presentation.components.UserSession;

public class LoginInjector {

    private ApplicationScope applicationScope;

    private static LoginInjector instance = null;

    private LoginInjector() {
    }

    public static LoginInjector getInstance() {
        if (instance == null) {
            instance = new LoginInjector();
        }
        return instance;
    }

    public void inject(LoginActivity activity) {
        applicationScope = ((TodoApplication) activity.getApplication()).getApplicationScope();
        injectView(activity);
        injectObject(activity);
    }

    private void injectView(LoginActivity activity) {
        activity.progressDialog = new ProgressDialog(activity);
        activity.progressDialog.setIndeterminate(true);
        activity.progressDialog.setMessage("Logging in");
    }

    private void injectObject(LoginActivity activity) {
        activity.presenter = new LoginPresenter(provideNavigator(activity), provideAuthenticator(), UserSession.getInstance());
    }

    private LoginContract.Navigator provideNavigator(LoginActivity activity) {
        return new TodoNavigator(applicationScope.navigator(activity));
    }

    private AuthenticateUser provideAuthenticator() {
        return new AuthenticateUser(applicationScope.userRepository());
    }

    public void destroy() {
        instance = null;

    }
}
