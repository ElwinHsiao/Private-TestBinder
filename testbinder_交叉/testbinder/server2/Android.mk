LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
	mybinderserver2.cpp

LOCAL_SHARED_LIBRARIES := \
	libutils \
	libbinder
	
#LOCAL_C_INCLUDES := \

LOCAL_MODULE:= mybinderserver2
LOCAL_MODULE_TAGS := debug

include $(BUILD_EXECUTABLE)
