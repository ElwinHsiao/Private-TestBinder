#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
using namespace android;

class MyBinderListener : public BBinder
{
	virtual status_t    onTransact( uint32_t code,
                                    const Parcel& data,
                                    Parcel* reply,
                                    uint32_t flags = 0)
	{
		printf("MyBinderListener: in MyBinderListener::onTransact(), receice request, code=%d\n", code);
		return 0;
	}
};

MyBinderListener g_MyBinderListener;

int main(int argc, char** argv)
{
	printf("client2: geting server: \"MyBinderServer2\"\n");
    sp<IBinder> binder = defaultServiceManager()->getService(String16("MyBinderServer2"));
	Parcel data, reply;
	
	sp<MyBinderListener> pBBinder = &g_MyBinderListener;
	data.writeStrongBinder(pBBinder);
	printf("client2: translate callback to server2\n");
	binder->transact(3, data, &reply);
	
	ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool();
}

