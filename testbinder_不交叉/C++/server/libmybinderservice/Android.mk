LOCAL_PATH:= $(call my-dir)

#
# libmediaplayerservice
#

include $(CLEAR_VARS)

LOCAL_SRC_FILES:=               \
    MyBinderService.cpp      	\


LOCAL_SHARED_LIBRARIES :=     		\
	libcutils             			\
	libutils              			\
	libbinder             			\
	libmybinder			           	\

#LOCAL_STATIC_LIBRARIES := \

LOCAL_C_INCLUDES :=                 \
	$(LOCAL_PATH)/../include

LOCAL_MODULE:= libmybinderservice
LOCAL_MODULE_TAGS := debug

include $(BUILD_SHARED_LIBRARY)

include $(call all-makefiles-under,$(LOCAL_PATH))

