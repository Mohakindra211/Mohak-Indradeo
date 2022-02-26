package com.example.todoapp.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.todoapp.model.Todo;
import com.example.todoapp.util.ContactRoomDatabase;

import java.util.List;

public class TodoRepository {
    private TodoDao contactDao;
    private LiveData<List<Todo>> allContacts;

    public TodoRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();
    }
    public LiveData<List<Todo>> getAllData() {return allContacts;}

    public void insert(Todo contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() ->{
            contactDao.insert(contact);
        });
    }

    public void deleteById(int position) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() ->{
            contactDao.deleteById(position);
        });
    }
}
