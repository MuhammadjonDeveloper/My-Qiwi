package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

import java.util.ArrayList

@Dao
interface ValuesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(values: ArrayList<Values>)

    @Update
    fun update(values: Values)

    @Delete
    fun delete(values: Values)
}

