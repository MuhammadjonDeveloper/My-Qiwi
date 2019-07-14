package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import java.util.ArrayList

@Dao
interface CategoriesDao {

    @get:Query("select * from categories order by display_order asc")
    val all: List<Categories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: ArrayList<Categories>)

    @Update
    fun update(categories: Categories)

    @Delete
    fun delete(categories: Categories)

}
