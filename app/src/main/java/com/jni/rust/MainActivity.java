package com.jni.rust;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView getStringFromCpp;
    private TextView getStringFromRust;
    private TextView getByteFromString;
    private TextView initLog;
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
        getByteFromString = findViewById(R.id.get_byte_from_string);
        initLog = findViewById(R.id.call_log);
    }

    private void initListener() {
        getStringFromCpp.setOnClickListener(this);
        getStringFromRust.setOnClickListener(this);
        getByteFromString.setOnClickListener(this);
        initLog.setOnClickListener(this);
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
            case R.id.get_byte_from_string:
                byte[] resultArray = RustNative.getByteFromString("this is a java str");
                Log.d(TAG, Arrays.toString(resultArray));
                Log.d(TAG, new String(resultArray));
                break;
            case R.id.call_log:
                RustNative.callLog();
                break;
            default:
                break;
        }
    }
}