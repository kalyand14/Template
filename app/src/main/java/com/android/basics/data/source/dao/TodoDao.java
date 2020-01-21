package com.android.basics.data.source.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.android.basics.data.source.entity.TodoTbl;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TodoTbl todoTbl);

    @Delete
    void delete(TodoTbl todoTbl);

    @Update
    void update(TodoTbl todoTbl);

    @Query("SELECT * from todo WHERE userId =:userId ORDER BY todoId DESC")
    List<TodoTbl> getAllTodo(int userId);

}
