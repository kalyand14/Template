package com.android.basics.presentation.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.basics.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;

    ProgressDialog progressDialog;

    Button btnLogin;

    EditText edtUserName;
    EditText edtPassword;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = findViewById(R.id.btn_login);
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        builder = new AlertDialog.Builder(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.OnLoginClick(edtUserName.getText().toString(), edtPassword.getText().toString());
            }
        });

        LoginInjector.getInstance().inject(this);
        this.presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.detach();
        LoginInjector.getInstance().destroy();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
        progressDialog.cancel();
    }

    @Override
    public void showAuthenticationError() {

        runOnUiThread(() -> {
            edtUserName.setText("");
            edtPassword.setText("");

            //Setting message manually and performing action on button click
            builder.setMessage("There was a problem logging in. Check your credentials or create an account.")
                    .setCancelable(false)
                    .setPositiveButton("Ok", (dialog, id) -> {
                        dialog.dismiss();
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.setTitle("Error");
            alert.show();
        });


    }
}
