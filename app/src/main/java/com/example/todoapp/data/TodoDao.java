package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.todoapp.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    //crud operation
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Todo contact);

    @Query("DELETE FROM Todo WHERE id=:position")
    void deleteById(int position);

    @Query("DELETE FROM Todo")
    void deleteAll();

    @Query(("SELECT * FROM Todo ORDER BY name ASC"))
    LiveData<List<Todo>> getAllContacts();


}
