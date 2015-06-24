LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
	mybinderclient2.cpp

LOCAL_SHARED_LIBRARIES := \
	libutils \
	libbinder

LOCAL_MODULE:= mybinderclient2
LOCAL_MODULE_TAGS := debug

include $(BUILD_EXECUTABLE)
