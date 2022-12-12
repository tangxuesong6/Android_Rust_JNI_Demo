package com.jni.rust;

import android.util.Log;

public class NativeSingleton {
    private NativeSingleton() {
    }

    public static NativeSingleton getInstance() {
        return NativeSingleton.SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final NativeSingleton sInstance = new NativeSingleton();
    }

    public void logIdentityHashCode() {
        Log.d("NativeSingleton", System.identityHashCode(this) + " ");
    }

}
