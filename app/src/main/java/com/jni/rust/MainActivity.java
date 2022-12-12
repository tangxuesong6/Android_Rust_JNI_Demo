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
    private TextView callLog;
    private TextView syncCallback;

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
        callLog = findViewById(R.id.call_log);
        syncCallback = findViewById(R.id.sync_callback);
    }

    private void initListener() {
        getStringFromCpp.setOnClickListener(this);
        getStringFromRust.setOnClickListener(this);
        getByteFromString.setOnClickListener(this);
        callLog.setOnClickListener(this);
        syncCallback.setOnClickListener(this);
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
            case R.id.sync_callback:
                RustNative.syncCallback(new RustListener() {
                    @Override
                    public void onStringCallback(String msg) {
                        Log.d(TAG, "sync callback: " + msg);
                    }

                    @Override
                    public void onVoidCallback() {
                        Log.d(TAG, "sync callback");

                    }
                });
                break;
            default:
                break;
        }
    }
}