#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_jni_rust_CNative_getStringFromCpp(JNIEnv *env, jclass clazz) {
    std::string bro = "hi bro from cpp";
    return env->NewStringUTF(bro.c_str());
}