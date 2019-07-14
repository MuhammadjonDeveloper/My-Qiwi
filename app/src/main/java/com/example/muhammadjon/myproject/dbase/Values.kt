package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Values {


    @PrimaryKey(autoGenerate = true)
    var id_values: Long = 0
    var id: Long = 0
    var field_id: Long = 0
    var field_value: String? = null
    var field_label_ru: String? = null
    var field_label_en: String? = null
    var field_label_uz: String? = null
    var amount: Long = 0
    var prefix: Long = 0
    var parent_id: Long = 0
    var check_id: Long = 0
    var display_order: Long = 0
}
