package com.android.basics.domain.interactor;

import com.android.basics.core.Callback;
import com.android.basics.core.mvp.UseCase;
import com.android.basics.domain.model.User;
import com.android.basics.domain.repository.UserRepository;

public class AuthenticateUser extends UseCase<AuthenticateUser.Param, User> {

    private UserRepository userRepository;

    public AuthenticateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void executeTask(Param param, final Callback<User> callback) {

        User user = new User();
        user.setUserName(param.userName);
        user.setPassword(param.password);

        this.userRepository.authenticate(user, new Callback<User>() {
            @Override
            public void onResponse(User response) {
                if (!isDisposed()) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onError(String errorcode, String errorResponse) {
                if (!isDisposed()) {
                    callback.onError(errorcode, errorResponse);
                }
            }
        });

    }

    public static final class Param {
        private String userName;
        private String password;

        public Param(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }
}
