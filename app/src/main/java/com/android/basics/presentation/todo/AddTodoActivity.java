package com.android.basics.presentation.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.basics.R;

public class AddTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
    }
}
