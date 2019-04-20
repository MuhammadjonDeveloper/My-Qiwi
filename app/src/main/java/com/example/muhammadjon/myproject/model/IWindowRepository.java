package com.example.muhammadjon.myproject.model;

import android.arch.lifecycle.LiveData;

import com.example.muhammadjon.myproject.dbase.Merchants;

import java.util.List;

public interface IWindowRepository {
  LiveData<List<Merchants>> search(int categoryid);
}
