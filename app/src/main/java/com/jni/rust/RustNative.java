package com.jni.rust;

public class RustNative {
    static {
        System.loadLibrary("rust_jni_demo");
    }

    public static native String getStringFromRust();

    public static native byte[] getByteFromString(String java_str);

    public static native void callLog();

    public static native void syncCallback(RustListener listener);

    public static native void asyncCallback(RustListener listener);

    public static native void singleton();

    public static native String getSignatureNormal();

}
