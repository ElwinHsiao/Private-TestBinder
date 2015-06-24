#include <binder/IServiceManager.h>
#include <unistd.h>		//for LOGI

#include "MyBinderService.h"

namespace android {

void MyBinderService::instantiate()
{
	defaultServiceManager()->addService(
            String16("com.study.MyBinderService.IMyBinderServer"), new MyBinderService());
	printf("register server: \"com.study.MyBinderService.IMyBinderServer\"\n");
}

int MyBinderService::getValue()
{
	LOGI("MyBinderService: receive request\n");
	return 99;
}


};	//namespace android 