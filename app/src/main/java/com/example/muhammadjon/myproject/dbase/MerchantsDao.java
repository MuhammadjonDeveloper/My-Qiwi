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
public interface MerchantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<Merchants> merchants);

    @Update
    void update(Merchants merchants);

    @Delete
    void delete(Merchants merchants);

    @Query("select * from merchants where category_id = :categoryId")
    List<Merchants> getAll(long categoryId);
}
