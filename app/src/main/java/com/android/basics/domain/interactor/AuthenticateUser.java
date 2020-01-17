package com.android.basics.domain.interactor;

import com.android.basics.core.mvp.UseCase;
import com.android.basics.core.Callback;
import com.android.basics.domain.model.User;
import com.android.basics.domain.repository.UserRepository;

public class AuthenticateUser extends UseCase<User, User> {

    private UserRepository userRepository;

    public AuthenticateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void executeTask(User param, final Callback<User> callback) {

        this.userRepository.authenticate(param, new Callback<User>() {
            @Override
            public void onResponse(User response) {
                if (!isDisposed()) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                if (!isDisposed()) {
                    callback.onError("E1", "Could not able to authenticate!!!");
                }
            }
        });

    }
}
