package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Categories {
    @PrimaryKey
    var id: Long = 0
    @ColumnInfo(name = "name_ru")
    var name_ru: String? = null

    @ColumnInfo(name = "name_uz")
    var name_uz: String? = null
    @ColumnInfo(name = "name_en")
    var name_en: String? = null

    var display_order: Long = 0
}
