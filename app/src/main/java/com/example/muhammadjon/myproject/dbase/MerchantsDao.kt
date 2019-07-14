package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import java.util.ArrayList

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface MerchantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(merchants: ArrayList<Merchants>)

    @Update
    fun update(merchants: Merchants)

    @Delete
    fun delete(merchants: Merchants)

    @Query("select * from merchants where category_id = :categoryId")
    fun getAll(categoryId: Long): Maybe<List<Merchants>>
}
