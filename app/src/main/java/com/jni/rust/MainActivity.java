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
    private TextView asyncCallback;
    private TextView javaCallSingleton;
    private TextView rustCallSingleton;
    private TextView rustGetSignatureNormal;


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
        asyncCallback = findViewById(R.id.async_callback);
        javaCallSingleton = findViewById(R.id.java_call_singleton);
        rustCallSingleton = findViewById(R.id.rust_call_singleton);
        rustGetSignatureNormal = findViewById(R.id.rust_get_sign_normal);
    }

    private void initListener() {
        getStringFromCpp.setOnClickListener(this);
        getStringFromRust.setOnClickListener(this);
        getByteFromString.setOnClickListener(this);
        callLog.setOnClickListener(this);
        syncCallback.setOnClickListener(this);
        asyncCallback.setOnClickListener(this);
        javaCallSingleton.setOnClickListener(this);
        rustCallSingleton.setOnClickListener(this);
        rustGetSignatureNormal.setOnClickListener(this);
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
            case R.id.async_callback:
                RustNative.asyncCallback(new RustListener() {
                    @Override
                    public void onStringCallback(String msg) {
                        Log.d(TAG, "async callback: " + msg);
                    }

                    @Override
                    public void onVoidCallback() {
                        Log.d(TAG, "async callback");
                    }
                });
                break;
            case R.id.java_call_singleton:
                NativeSingleton.getInstance().logIdentityHashCode();
                break;
            case R.id.rust_call_singleton:
                RustNative.singleton();
                break;
            case R.id.rust_get_sign_normal:
                String sign = RustNative.getSignatureNormal();
                Log.d(TAG, sign);
                break;
            default:
                break;
        }
    }
}