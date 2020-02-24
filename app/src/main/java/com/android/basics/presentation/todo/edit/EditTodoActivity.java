package com.android.basics.presentation.todo.edit;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.basics.R;

import java.util.Calendar;

public class EditTodoActivity extends AppCompatActivity implements EditTodoContract.View {

    public EditTodoContract.Presenter presenter;
    public ProgressDialog progressDialog;
    public AlertDialog.Builder builder;

    public EditText edtName;
    public EditText edtDescription;
    public EditText edtDate;
    public Button btnSubmit;
    public Button btnDelete;
    public ImageButton btnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        setTitle("Edit Todo");

        builder = new AlertDialog.Builder(this);

        edtName = findViewById(R.id.edt_todo_name);
        edtDescription = findViewById(R.id.edt_todo_description);
        edtDate = findViewById(R.id.edt_todo_date);

        btnSubmit = findViewById(R.id.btn_edit_todo);
        btnDelete = findViewById(R.id.btn_edit_delete);
        btnDate = findViewById(R.id.btn_edit_date);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSubmit(edtName.getText().toString(), edtDescription.getText().toString(), edtDate.getText().toString());
            }
        });

        btnDelete.setOnClickListener(view -> presenter.onDelete());

        btnDate.setOnClickListener(view -> presenter.onSelectDate());

        EditTodoInjector.getInstance().inject(this);

        presenter.attach(this);

        presenter.loadTodo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
        progressDialog.cancel();
    }

    @Override
    public void showSuccessDialog(String message) {
        //Setting message manually and performing action on button click
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, id) -> {
                    dialog.dismiss();
                    presenter.navigate();
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void showErrorDialog(String message) {
        //Setting message manually and performing action on button click
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, id) -> {
                    dialog.dismiss();
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.setTitle("Error");
        alert.show();
    }

    @Override
    public void showDatePickerDialog() {
        int year;
        int month;
        int day;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, dayOfMonth) ->
                        edtDate.setText(dayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear),
                year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void setName(String name) {
        edtName.setText(name);
    }

    @Override
    public void setDescription(String description) {
        edtDescription.setText(description);
    }

    @Override
    public void setDate(String date) {
        edtDate.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cancel:
                presenter.onCancel();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
