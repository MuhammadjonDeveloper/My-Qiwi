package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Fields {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var merchantId: Long = 0
    var name: String? = null
    var type: String? = null
    var labelRu: String? = null
    var labelUz: String? = null
    var labelEn: String? = null
    var fieldSize: Long = 0
    var controlType: String? = null
    var controlTypeInfo: String? = null
    var parentId: Long = 0
    var ord: Long = 0
}
