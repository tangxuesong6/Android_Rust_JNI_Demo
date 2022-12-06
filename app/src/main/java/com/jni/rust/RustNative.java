package com.jni.rust;

public class RustNative {
    static {
        System.loadLibrary("rust_jni_demo");
    }
    public static native String getStringFromRust();
}
