package com.example.muhammadjon.myproject.dbase

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Merchants {
    @PrimaryKey
    var id: Long = 0
    var category_id: Long = 0
    var name_ru: String? = null
    var name_uz: String? = null
    var name_en: String? = null
    var topselected: Long = 0
    var info_service_id: Long = 0
    var payment_service_id: Long = 0
    var cancel_service_id: Long = 0
    var min_amount: Long = 0
    var max_amount: Long = 0
    var display_order: Long = 0
    var legal_name: String? = null
    var service_price: Long = 0
    var print_info_cheque: Long = 0
    var print_pay_cheque: Long = 0
}
