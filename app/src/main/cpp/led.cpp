//
// Created by namhoon on 16. 12. 18.
//
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

void
Java_com_example_namhoon_nativecexample_MainActivity_writeLed(
        JNIEnv *env,
        jobject /* this */,
        jstring string) {

    const char *str = env->GetStringUTFChars(string, 0);
    syscall(__NR_led_write, str);
    env->ReleaseStringUTFChars(string, str);
    return;
}