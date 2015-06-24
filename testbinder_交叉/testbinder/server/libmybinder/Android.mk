LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)


include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    IMyBinderService.cpp \
 
LOCAL_SHARED_LIBRARIES := \
	libui libcutils libutils libbinder


LOCAL_MODULE:= libmybinder
LOCAL_MODULE_TAGS := debug

LOCAL_C_INCLUDES := \
    $(LOCAL_PATH)/../include
	
#include $(BUILD_STATIC_LIBRARY)
include $(BUILD_SHARED_LIBRARY)
