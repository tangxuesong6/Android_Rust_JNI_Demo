package com.jni.rust;

public class CNative {
    // Used to load the 'rust' library on application startup.
    static {
        System.loadLibrary("cppnative");
    }
    public static native String getStringFromCpp();

}
