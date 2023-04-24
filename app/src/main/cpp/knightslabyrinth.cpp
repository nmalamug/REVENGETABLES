// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("knightslabyrinth");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("knightslabyrinth")
//      }
//    }


#include <jni.h>
#include <string>
#include <cmath>
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_getNativeMessage(JNIEnv *env, jobject ) {
    std::string message = "Hello from C++!";
    return env->NewStringUTF(message.c_str());
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_getNewTick(JNIEnv *env, jobject thisObject)
{
    return;
}


