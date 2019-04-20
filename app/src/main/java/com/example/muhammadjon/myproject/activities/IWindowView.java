package com.example.muhammadjon.myproject.activities;

import com.example.muhammadjon.myproject.dbase.Merchants;

import java.util.List;

public interface IWindowView {

    void onHidePb();

    void onShowPb();

    void onResult(List<Merchants> merchants);

    void onError(String text);
}
