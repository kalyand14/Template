package com.android.basics.presentation.todo.add;

import com.android.basics.core.mvp.BasePresenter;

public interface AddTodoContract {
    interface View {
        void showProgressDialog();

        void dismissProgressDialog();

        void showSuccessDialog();

        void showErrorDialog();
    }

    interface Presenter extends BasePresenter<AddTodoContract.View> {
        void onSubmit(String name, String desc, String date);

        void navigate();

        void OnCancel();
    }

    interface Navigator {
        void goToHomeScreen();
    }
}
