package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.ArrayList;

@Dao
public interface FieldsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<Fields> fields);

    @Update
    void update(Fields fields);

    @Delete
    void delete(Fields fields);
}
