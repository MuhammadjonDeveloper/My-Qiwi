package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<Categories> categories);

    @Update
    void update(Categories categories);

    @Delete
    void delete(Categories categories);

    @Query("select * from categories order by display_order asc")
    List<Categories> getAll();

}
