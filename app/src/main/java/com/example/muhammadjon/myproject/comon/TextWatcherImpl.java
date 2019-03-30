package com.example.muhammadjon.myproject.comon;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatcherImpl implements TextWatcher {
    private IWatcher iWatcher;

    public TextWatcherImpl(IWatcher iWatcher) {
        this.iWatcher = iWatcher;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence,
                                  int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence c, int i, int i1, int i2) {
        iWatcher.onTextChanged(c.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
