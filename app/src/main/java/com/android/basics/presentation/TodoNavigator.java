package com.android.basics.presentation;

import android.content.Intent;

import com.android.basics.core.navigation.Navigator;
import com.android.basics.presentation.login.LoginActivity;
import com.android.basics.presentation.login.LoginContract;
import com.android.basics.presentation.registration.RegisterUserActivity;
import com.android.basics.presentation.splash.SplashContract;

public class TodoNavigator implements SplashContract.Navigator, LoginContract.Navigator {
    private Navigator navigator;

    public TodoNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void goToLoginScreen() {
        Intent intent = navigator.createIntent(LoginActivity.class);
        navigator.launchActivity(intent);
        navigator.finishActivity();
    }

    @Override
    public void goToRegisterScreen() {
        Intent intent = navigator.createIntent(RegisterUserActivity.class);
        navigator.launchActivity(intent);
    }
}
