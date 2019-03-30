package com.example.muhammadjon.myproject.dbase;

import java.util.ArrayList;

public class MyPojo {

    private ArrayList<String> deleted;

    private ArrayList<Values> values;

    private ArrayList<Categories> categories;

    private ArrayList<Merchants> merchants;

    private ArrayList<Fields> fields;


    public ArrayList<String> getDeleted() {
        return deleted;
    }

    public void setDeleted(ArrayList<String> deleted) {
        this.deleted = deleted;
    }

    public ArrayList<Values> getValues() {
        return values;
    }

    public void setValues(ArrayList<Values> values) {
        this.values = values;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public ArrayList<Merchants> getMerchants() {
        return merchants;
    }

    public void setMerchants(ArrayList<Merchants> merchants) {
        this.merchants = merchants;
    }

    public ArrayList<Fields> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Fields> fields) {
        this.fields = fields;
    }
}
