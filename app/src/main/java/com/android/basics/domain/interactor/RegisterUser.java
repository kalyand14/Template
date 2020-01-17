package com.android.basics.domain.interactor;

import com.android.basics.core.Callback;
import com.android.basics.core.mvp.UseCase;
import com.android.basics.domain.model.User;
import com.android.basics.domain.repository.UserRepository;

public class RegisterUser extends UseCase<User, User> {

    private UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void executeTask(User param, final Callback<User> callback) {
        this.userRepository.register(param, new Callback<User>() {
            @Override
            public void onResponse(User response) {
                if (!isDisposed()) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                if (!isDisposed()) {
                    callback.onError("E0", "Could not able to register!!!");
                }
            }
        });
    }
}
