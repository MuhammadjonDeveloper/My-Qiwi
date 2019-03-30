package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Categories {
    @PrimaryKey()
    private long id;
    @ColumnInfo(name = "name_ru")
    private String name_ru;

    @ColumnInfo(name = "name_uz")
    private String name_uz;
    @ColumnInfo(name = "name_en")
    private String name_en;

    private long display_order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getName_uz() {
        return name_uz;
    }

    public void setName_uz(String name_uz) {
        this.name_uz = name_uz;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public long getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(long display_order) {
        this.display_order = display_order;
    }
}
