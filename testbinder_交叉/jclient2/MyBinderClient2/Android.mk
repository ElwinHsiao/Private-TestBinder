LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

#LOCAL_JAVA_LIBRARIES := bouncycastle
#OCAL_STATIC_JAVA_LIBRARIES := guava

LOCAL_SRC_FILES := $(call all-java-files-under, src) \
LOCAL_SRC_FILES += $(LOCAL_PATH)/src/com/study/MyBinderService/IMyBinderServer.aidl

LOCAL_PACKAGE_NAME := MyBinderClient2
#LOCAL_CERTIFICATE := platform
LOCAL_MODULE_TAGS := debug

LOCAL_DEX_PREOPT := false

LOCAL_PROGUARD_FLAG_FILES := proguard.flags

include $(BUILD_PACKAGE)

# Use the folloing include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
