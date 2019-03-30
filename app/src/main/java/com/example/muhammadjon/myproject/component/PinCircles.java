package com.example.muhammadjon.myproject.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.muhammadjon.myproject.R;

import java.util.ArrayList;

public class PinCircles extends FrameLayout {
    private ArrayList<View> circles = new ArrayList<>();
    private View one, two, three, four, five, six;

    private int count = 4;
    private Drawable emptyBachground, fillBachground;

    public PinCircles(Context context) {
        super(context);
        initialize();
    }


    public PinCircles(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public PinCircles(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PinCircles(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.layout_circles, this);

        emptyBachground = getContext().getResources().getDrawable(R.drawable.circle_pin_empty);
        fillBachground = getContext().getResources().getDrawable(R.drawable.circle_pin_fill);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);

        circles.add(one);
        circles.add(two);
        circles.add(three);
        circles.add(four);
        circles.add(five);
        circles.add(six);

        for (int i = 0; i < circles.size(); i++) {
            if (i < count) circles.get(i).setVisibility(VISIBLE);
            else circles.get(i).setVisibility(GONE);
        }
    }

    public void setCircles(ArrayList<View> circles) {
        this.circles = circles;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void fillCircles(int fillCount) {
        for (int i = 0; i < count; i++) {
            if (i < fillCount) circles.get(i).setBackground(fillBachground);
            else circles.get(i).setBackground(emptyBachground);
        }
    }
}
