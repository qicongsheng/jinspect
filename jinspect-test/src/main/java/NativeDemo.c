#include "NativeDemo.h"
#include <stdio.h>
#include <memory.h>
#include <string.h>
#include <jvmti.h>
#include <jni.h>

jint JNICALL heapFRCallback(
		jvmtiHeapReferenceKind reference_kind,
		const jvmtiHeapReferenceInfo* reference_info, 
		jlong class_tag,
		jlong referrer_class_tag, 
		jlong size, 
		jlong* tag_ptr,
		jlong* referrer_tag_ptr, 
		jint length, 
		void* user_data) {
	
	return JVMTI_VISIT_OBJECTS;
}

JNIEXPORT jint JNICALL Java_NativeDemo_sayHello(JNIEnv* env, jobject jobj, jobject targetObj){

    jint rc = 0;
	jvmtiError error;
	JavaVM *javaVM;
	jvmtiEnv *jvmti;
	jvmtiCapabilities capabilities;
	rc = (*env)->GetJavaVM(env, &javaVM);
	if (0 != rc) {
		fprintf(stderr, "GetJavaVM Error!");
		return 0;
	}
	fprintf(stderr, "GetJavaVM success!");
	
	rc = (*javaVM)->GetEnv(javaVM, (void **)&jvmti, JVMTI_VERSION_1);
    if (rc != JNI_OK) {
        fprintf(stderr, "GetEnv Error!");
    }
	fprintf(stderr, "GetEnv success!");
	// add capabilities
	(void)memset(&capabilities,0, sizeof(capabilities));
    capabilities.can_tag_objects  = 1;
    capabilities.can_get_line_numbers  = 1;
    //capabilities.can_generate_method_entry_events  = 1;  
	error = (*jvmti)->AddCapabilities(jvmti, &capabilities);
	
	if(error != JVMTI_ERROR_NONE) {
		fprintf(stderr, "ERROR: Unable to AddCapabilities JVMTI");
		return 0;
	}
	
	jint result = 0;
	
	fprintf(stderr, "fuckkk22");
	return result;
} 