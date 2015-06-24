LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
	main_mybinderserver.cpp 

LOCAL_SHARED_LIBRARIES := \
	libmybinderservice \
	libutils \
	libbinder
	
LOCAL_C_INCLUDES := \
    $(LOCAL_PATH)/../libmybinderservice	\
	$(LOCAL_PATH)/../include

LOCAL_MODULE:= mybinderserver
LOCAL_MODULE_TAGS := debug

include $(BUILD_EXECUTABLE)
