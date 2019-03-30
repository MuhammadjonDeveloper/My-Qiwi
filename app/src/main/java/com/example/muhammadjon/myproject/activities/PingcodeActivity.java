package com.example.muhammadjon.myproject.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.component.PinCircles;
import com.example.muhammadjon.myproject.component.PinKeyboard;

public class PingcodeActivity extends AppCompatActivity {
    private LinearLayout pinContainer;
    //    private LottieAnimationView animationView;
    private TextView pinInfoText;
    private PinCircles circles;
    private PinKeyboard pinKeyboard;
    private Animation animation;

    private SharedPreferences preferences;
    private String pinCode = "";
    private String tempPin = null;
    private Boolean createPinCode;
    private String pass;
    private int counter = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            pass = arg.getString("key");
        }
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        createPinCode = preferences.getBoolean("is_counter", true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorYellow));
        }


        animation = AnimationUtils.loadAnimation(this, R.anim.shake);

        pinKeyboard = findViewById(R.id.pin_keyboard);
        circles = findViewById(R.id.pin_circles);
        pinInfoText = findViewById(R.id.pin_info_text);

        pinKeyboard.setOnTabListener(new PinKeyboard.TabListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onNumberTab(String number) {
                setPinCode(pinCode + number);
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onEraseTab() {
                if (pinCode.length() > 0) {
                    setPinCode(pinCode.substring(0, pinCode.length() - 1));
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setPinCode(String pinCode) {
        if (pinCode.length() <= 4) {
            this.pinCode = pinCode;
        }
        circles.fillCircles(this.pinCode.length());
        if (this.pinCode.length() == 4) {
            new Handler().postDelayed(() -> {
                if (this.pinCode.length() == 4)
                    checkPin();

            }, 200);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkPin() {
        if (createPinCode) {
            if (counter == 2) {
                preferences.edit().putBoolean("is_counter", false).apply();
                preferences.edit().putString("is_correct", pinCode).apply();
                startActivity(new Intent(PingcodeActivity.this, MainActivity.class));
                finish();
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setPinCode("");
                    }
                },1000);
                counter++;
            }
        } else {
            if (pinCode.equals(preferences.getString("is_correct", pinCode))) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                pinInfoText.setText("Pin code error");
                circles.startAnimation(animation);
                setPinCode("");
            }
        }

    }
}
