package com.android.basics.presentation.todo.edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.basics.R;

public class EditTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        setTitle("Edit Todo");
    }
}
