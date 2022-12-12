package com.jni.rust;

public interface RustListener {
    void onStringCallback(String msg);

    void onVoidCallback();
}
