package com.example.muhammadjon.myproject.component;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.muhammadjon.myproject.R;

public class PinKeyboard extends FrameLayout implements View.OnClickListener {
    private TabListener listener;
    private TextView one, two, three, four, five, six, seven, eight, nine, zero;
    private LinearLayout erase;

    public PinKeyboard(Context context) {
        super(context);
        initialize();
    }

    public PinKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public PinKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PinKeyboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.layout_pin_keyboard, this);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        erase = findViewById(R.id.erase);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);

        erase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.erase:
                if (listener != null) {
                    listener.onEraseTab();
                }
                break;
            default:
                if (listener != null) {
                    listener.onNumberTab(((TextView) findViewById(v.getId())).getText().toString());
                }
                break;
        }
    }

    public void setOnTabListener(TabListener listener) {
        this.listener = listener;
    }

    public interface TabListener {

        void onNumberTab(String number);

        void onEraseTab();
    }
}
