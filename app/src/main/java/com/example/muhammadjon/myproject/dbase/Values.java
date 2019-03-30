package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Values {


    @PrimaryKey(autoGenerate = true)
    private long id_values;
    private long id;
    private long field_id;
    private String field_value;
    private String field_label_ru;
    private String field_label_en;
    private String field_label_uz;
    private long amount;
    private long prefix;
    private long parent_id;
    private long check_id;
    private long display_order;

    public long getId_values() {
        return id_values;
    }

    public void setId_values(long id_values) {
        this.id_values = id_values;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getField_id() {
        return field_id;
    }

    public void setField_id(long field_id) {
        this.field_id = field_id;
    }

    public String getField_value() {
        return field_value;
    }

    public void setField_value(String field_value) {
        this.field_value = field_value;
    }

    public String getField_label_ru() {
        return field_label_ru;
    }

    public void setField_label_ru(String field_label_ru) {
        this.field_label_ru = field_label_ru;
    }

    public String getField_label_en() {
        return field_label_en;
    }

    public void setField_label_en(String field_label_en) {
        this.field_label_en = field_label_en;
    }

    public String getField_label_uz() {
        return field_label_uz;
    }

    public void setField_label_uz(String field_label_uz) {
        this.field_label_uz = field_label_uz;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getPrefix() {
        return prefix;
    }

    public void setPrefix(long prefix) {
        this.prefix = prefix;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public long getCheck_id() {
        return check_id;
    }

    public void setCheck_id(long check_id) {
        this.check_id = check_id;
    }

    public long getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(long display_order) {
        this.display_order = display_order;
    }
}
