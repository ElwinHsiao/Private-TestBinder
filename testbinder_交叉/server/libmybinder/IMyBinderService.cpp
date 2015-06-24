#include <binder/Parcel.h>
#include <unistd.h>		//for LOGI

#include "IMyBinderService.h"

namespace android {
enum 
{
    GET_VALUE = 1
};

class BpMyBinderService: public BpInterface<IMyBinderService>
{
public:
    BpMyBinderService(const sp<IBinder>& impl)
        : BpInterface<IMyBinderService>(impl)
    {
    }
	
	int getValue()
    {
	    Parcel data, reply;
        data.writeInterfaceToken(IMyBinderService::getInterfaceDescriptor());
        remote()->transact(GET_VALUE, data, &reply);
		LOGI("Bp: request sended!\n");
		printf("Bp: request sended!\n");
        return reply.readInt32();
	}
};

IMPLEMENT_META_INTERFACE(MyBinderService, "test.study.IMyBinderService");

// ----------------------------------------------------------------------

status_t BnMyBinderService::onTransact(
    uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags)
{
	LOGI("Bn: request receiced!\n");
	printf("Bn: request receiced!\n");
    switch(code) 
	{
        case GET_VALUE: 
		{
			printf("Bn: CHECK_INTERFACE 1\n");
            CHECK_INTERFACE(IMyBinderService, data, reply);

			LOGI("Bn: b value send!\n");
			printf("Bn: b value send!\n");
            reply->writeInt32(getValue());
			LOGI("Bn: a value sended!\n");
			printf("Bn: a value sended!\n");
            return 0;
			//break;
        }
		
		default:
			printf("Bn: CHECK_INTERFACE 2\n");
            CHECK_INTERFACE(IMyBinderService, data, reply);
			
			LOGI("Bn: b default value sended!\n");
			printf("Bn: b default value sended!\n");
            reply->writeInt32(44);
			printf("Bn: a default value sended!\n");
			LOGI("Bn: a default value sended!\n");
            return 0;
	}

	LOGI("Bn: onTransact return...\n");
	printf("Bn: onTransact return...\n");
}


};	//namespace android