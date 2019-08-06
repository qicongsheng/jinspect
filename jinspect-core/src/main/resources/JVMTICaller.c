#include "JVMTICaller.h"
#include <stdio.h>
#include <memory.h>
#include <string.h>
#include <jvmti.h>
#include <jni.h>

static jobject jThreadListenerHandler = NULL;
static jclass thread_listener_handler_clazz = NULL;

void JNICALL callbackThreadStartHook(jvmtiEnv *jvmti_env,
            JNIEnv* env,
            jthread thread) {
	jmethodID threadStartMethod = (*env)->GetMethodID(env, thread_listener_handler_clazz, "onThreadStart", "(Ljava/lang/Thread;)V");
    (*env)->CallObjectMethod(env, jThreadListenerHandler, threadStartMethod, thread);

}

void JNICALL callbackThreadEndHook(jvmtiEnv *jvmti_env,
            JNIEnv* env,
            jthread thread) {
	jmethodID threadEndMethod = (*env)->GetMethodID(env, thread_listener_handler_clazz, "onThreadEnd", "(Ljava/lang/Thread;)V");
    (*env)->CallObjectMethod(env, jThreadListenerHandler, threadEndMethod, thread);

}

JNIEXPORT void JNICALL Java_org_jinspect_core_jvmti_JVMTICaller_threadStartAndEndCallback(JNIEnv* env, jobject jobj, jobject listenerHandler){

    jint rc = 0;
	jvmtiError error;
	JavaVM *javaVM;
	jvmtiEnv *jvmti;
	jvmtiCapabilities capabilities;
	rc = (*env)->GetJavaVM(env, &javaVM);
	if (0 != rc) {
		fprintf(stderr, "GetJavaVM Error!");
		return;
	}
	fprintf(stderr, "GetJavaVM success!");
	
	rc = (*javaVM)->GetEnv(javaVM, (void **)&jvmti, JVMTI_VERSION_1);
    if (rc != JNI_OK) {
        fprintf(stderr, "GetEnv Error!");
    }
	fprintf(stderr, "GetEnv success!");
	
	// 设置thread listener全局变量
	jThreadListenerHandler = (*env)->NewGlobalRef(env, listenerHandler);
	jclass thread_listener_handler_clazz_local = (*env)->GetObjectClass(env, listenerHandler);
	thread_listener_handler_clazz = (*env)->NewGlobalRef(env, thread_listener_handler_clazz_local);
	
	/*
	jmethodID threadEndMethod = (*env)->GetMethodID(env, thread_listener_handler_clazz, "onThreadEnd", "(Ljava/lang/Thread;)V");
	jThreadListenerHandler_start_method = (*env)->NewGlobalRef(env, threadStartMethod);
	jThreadListenerHandler_end_method = (*env)->NewGlobalRef(env, threadEndMethod);
	*/
	
	// add capabilities
	(void)memset(&capabilities,0, sizeof(capabilities));
    capabilities.can_tag_objects  = 1;
    capabilities.can_get_line_numbers  = 1;
	error = (*jvmti)->AddCapabilities(jvmti, &capabilities);
	
	if(error != JVMTI_ERROR_NONE) {
		fprintf(stderr, "ERROR: Unable to AddCapabilities JVMTI");
		return;
	}
	
	//设置JVM事件回调
	jvmtiEventCallbacks callbacks;
	callbacks.ThreadStart = &callbackThreadStartHook;
	callbacks.ThreadEnd = &callbackThreadEndHook;
	error = (*jvmti)->SetEventCallbacks(jvmti, &callbacks, (jint)sizeof(callbacks));
	if(error != JVMTI_ERROR_NONE) {
		fprintf(stderr, "ERROR: Unable to SetEventCallbacks JVMTI!");
		return;
	}

	//设置事件通知
	error = (*jvmti)->SetEventNotificationMode(jvmti, JVMTI_ENABLE, JVMTI_EVENT_THREAD_START, (jthread)NULL);
	if(error != JVMTI_ERROR_NONE) {
		fprintf(stderr, " ERROR: Unable to SetEventNotificationMode JVMTI_EVENT_THREAD_START JVMTI!");
		return ;
	}
	//设置事件通知
	error = (*jvmti)->SetEventNotificationMode(jvmti, JVMTI_ENABLE, JVMTI_EVENT_THREAD_END, (jthread)NULL);
	if(error != JVMTI_ERROR_NONE) {
		fprintf(stderr, " ERROR: Unable to SetEventNotificationMode JVMTI_EVENT_THREAD_END JVMTI!");
		return ;
	}
} 