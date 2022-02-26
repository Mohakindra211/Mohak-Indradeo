package com.example.todoapp;

import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todoapp.adapter.RecyclerViewAdapter;
import com.example.todoapp.model.Todo;
import com.example.todoapp.model.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener {

    private static final int NEW_ACTIVITY_REQUEST_CODE = 1;
    private TodoViewModel contactViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LiveData<List<Todo>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(TodoViewModel.class);


        contactViewModel.getAllContacts().observe(this, contacts -> {
            //setup adapter
            contactList = contactViewModel.getAllContacts();
            recyclerViewAdapter = new RecyclerViewAdapter(contactList, this, this);
            recyclerView.setAdapter(recyclerViewAdapter);
        });


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewTodo.class);
            startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Todo contact = new Todo(data.getStringExtra(NewTodo.NAME_REPLY),
                    data.getStringExtra(NewTodo.OCCUPATION));
            TodoViewModel.insert(contact);
        }
    }

    @Override
    public void onContactClick(int position) {
        Toast.makeText(this, "here "+position, Toast.LENGTH_LONG).show();
    }
}