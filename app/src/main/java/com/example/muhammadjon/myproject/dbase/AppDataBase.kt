package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Categories::class, Merchants::class, Fields::class, Values::class], version = 4, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract val categoryDao: CategoriesDao
    abstract val merchantDao: MerchantsDao
    abstract val fielsDao: FieldsDao
    abstract val valuesDao: ValuesDao

    companion object {
        private var instaince: AppDataBase? = null

        fun getInstaince(context: Context): AppDataBase {
            if (instaince == null) {
                instaince = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java, "dbqiwi.SQLite")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build() }
            return instaince as AppDataBase
        }

        fun distroyInstaince() {
            instaince = null
        }
    }
}
