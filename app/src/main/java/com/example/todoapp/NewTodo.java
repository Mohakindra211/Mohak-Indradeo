package com.example.todoapp;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.todoapp.model.TodoViewModel;

public class NewTodo extends AppCompatActivity {

    public static final String NAME_REPLY = "NAME_REPLY";
    public static final String OCCUPATION = "OCCUPATION";
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton;
    private TodoViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        enterName = findViewById(R.id.editTextName);
        enterOccupation = findViewById(R.id.editTextOccupation);
        saveInfoButton = findViewById(R.id.save_button);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewTodo.this
                .getApplication())
                .create(TodoViewModel.class);

        saveInfoButton.setOnClickListener(view -> {

            Intent replyIntent = new Intent();


            if (!TextUtils.isEmpty(enterName.getText())
                    && !TextUtils.isEmpty(enterOccupation.getText())) {

                String name = enterName.getText().toString();
                String occupation = enterOccupation.getText().toString();

                replyIntent.putExtra(NAME_REPLY, name);
                replyIntent.putExtra(OCCUPATION, occupation);
                setResult(RESULT_OK, replyIntent);

            }else {
                setResult(RESULT_CANCELED, replyIntent);
                Toast.makeText(this, "Field Missing !", Toast.LENGTH_LONG)
                        .show();
            }
            finish();

        });




    }
}