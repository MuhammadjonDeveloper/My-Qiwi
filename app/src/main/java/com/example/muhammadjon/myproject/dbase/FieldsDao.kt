package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

import java.util.ArrayList

@Dao
interface FieldsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fields: ArrayList<Fields>)

    @Update
    fun update(fields: Fields)

    @Delete
    fun delete(fields: Fields)
}
