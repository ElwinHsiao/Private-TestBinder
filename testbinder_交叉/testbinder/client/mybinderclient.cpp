#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>

#include "IMyBinderService.h"

using namespace android;

int main(int argc, char** argv)
{
	printf("geting server: \"com.study.MyBinderService.IMyBinderServer\"\n");
    sp<IBinder> binder = defaultServiceManager()->getService(String16("com.study.MyBinderService.IMyBinderServer"));
	sp<IMyBinderService> service = interface_cast<IMyBinderService>(binder);
	int value = service->getValue();
	printf("value from server is: %d\n", value);
}