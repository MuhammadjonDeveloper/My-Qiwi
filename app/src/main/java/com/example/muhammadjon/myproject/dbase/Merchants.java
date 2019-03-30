package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Merchants {
    @PrimaryKey()
    private long id;
    private long category_id;
    private String name_ru;
    private String name_uz;
    private String name_en;
    private long topselected;
    private long info_service_id;
    private long payment_service_id;
    private long cancel_service_id;
    private long min_amount;
    private long max_amount;
    private long display_order;
    private String legal_name;
    private long service_price;
    private long print_info_cheque;
    private long print_pay_cheque;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_uz() {
        return name_uz;
    }

    public void setName_uz(String name_uz) {
        this.name_uz = name_uz;
    }

    public long getTopselected() {
        return topselected;
    }

    public void setTopselected(long topselected) {
        this.topselected = topselected;
    }

    public long getInfo_service_id() {
        return info_service_id;
    }

    public void setInfo_service_id(long info_service_id) {
        this.info_service_id = info_service_id;
    }

    public long getPayment_service_id() {
        return payment_service_id;
    }

    public void setPayment_service_id(long payment_service_id) {
        this.payment_service_id = payment_service_id;
    }

    public long getCancel_service_id() {
        return cancel_service_id;
    }

    public void setCancel_service_id(long cancel_service_id) {
        this.cancel_service_id = cancel_service_id;
    }

    public long getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(long min_amount) {
        this.min_amount = min_amount;
    }

    public long getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(long max_amount) {
        this.max_amount = max_amount;
    }

    public long getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(long display_order) {
        this.display_order = display_order;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String legal_name) {
        this.legal_name = legal_name;
    }

    public long getService_price() {
        return service_price;
    }

    public void setService_price(long service_price) {
        this.service_price = service_price;
    }

    public long getPrint_info_cheque() {
        return print_info_cheque;
    }

    public void setPrint_info_cheque(long print_info_cheque) {
        this.print_info_cheque = print_info_cheque;
    }

    public long getPrint_pay_cheque() {
        return print_pay_cheque;
    }

    public void setPrint_pay_cheque(long print_pay_cheque) {
        this.print_pay_cheque = print_pay_cheque;
    }
}
