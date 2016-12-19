#include <jni.h>
#include <string>
#include <errno.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <sys/mman.h>
#include <sys/syscall.h>

#define __NR_seg_write 319
#define __NR_led_write 320

extern "C"

JNIEXPORT void JNICALL
Java_com_example_namhoon_nativecexample_MainActivity_writeDev(
        JNIEnv *env,
        jobject /* this */,
        jstring string, jint type) {

    const char *str = env->GetStringUTFChars(string, 0);
    if (type == 0) {
        syscall(__NR_seg_write, str);
    } else if (type == 1) {
        syscall(__NR_led_write, str);
    }
    env->ReleaseStringUTFChars(string, str);
    return;
}

//JNIEXPORT void JNICALL
//Java_com_example_namhoon_nativecexample_MainActivity_writeLed(
//        JNIEnv *env,
//        jobject /* this */,
//        jstring string) {
//
//    const char *str = env->GetStringUTFChars(string, 0);
//    syscall(__NR_led_write, str);
//    env->ReleaseStringUTFChars(string, str);
//    return;
//}


//jstring
//Java_com_example_namhoon_nativecexample_MainActivity_stringFromJNI(
//        JNIEnv *env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}
//
