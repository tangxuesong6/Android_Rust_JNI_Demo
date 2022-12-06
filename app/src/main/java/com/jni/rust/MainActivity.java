package com.jni.rust;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView getStringFromCpp;
    private TextView getStringFromRust;
    private static final String TAG = "RUST_JNI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        getStringFromCpp = findViewById(R.id.get_str_from_cpp);
        getStringFromRust = findViewById(R.id.get_str_from_rust);
    }

    private void initListener() {
        getStringFromCpp.setOnClickListener(this);
        getStringFromRust.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String result = "";
        switch (v.getId()) {
            case R.id.get_str_from_cpp:
                result = CNative.getStringFromCpp();
                Log.d(TAG, result);
                break;
            case R.id.get_str_from_rust:
                result = RustNative.getStringFromRust();
                Log.d(TAG, result);
                break;
            default:
                break;
        }
    }
}