LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
	mybinderclient.cpp

LOCAL_SHARED_LIBRARIES := \
	libmybinder \
	libutils \
	libbinder
	
LOCAL_C_INCLUDES := \
	$(LOCAL_PATH)/../server/include

LOCAL_MODULE:= mybinderclient
LOCAL_MODULE_TAGS := debug

include $(BUILD_EXECUTABLE)
