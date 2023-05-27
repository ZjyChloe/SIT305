package com.example.task7_1_2.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {

  @Query("SELECT * FROM item")
    List<Item> getAll();

  @Insert
    void insert(Item item);

  @Delete
    void delete(Item item);

  @Update
    void update(Item item);
}
