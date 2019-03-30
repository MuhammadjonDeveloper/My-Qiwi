package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.ArrayList;

@Dao
public interface ValuesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<Values> values);

    @Update
    void update(Values values);

    @Delete
    void delete(Values values);
}
