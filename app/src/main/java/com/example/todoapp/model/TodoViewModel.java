package com.example.todoapp.model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.todoapp.data.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    public static TodoRepository repository;
    public final LiveData<List<Todo>> allContacts;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
        allContacts = repository.getAllData();

    }

    public LiveData<List<Todo>> getAllContacts() {
        return allContacts;
    }

    public static void insert(Todo contact) {
        repository.insert(contact);
    }

    public static void deleteById(int position) { repository.deleteById(position); }

}
