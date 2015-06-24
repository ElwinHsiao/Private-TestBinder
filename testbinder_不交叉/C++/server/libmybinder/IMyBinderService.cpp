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
    switch(code) {
        case GET_VALUE: {
            CHECK_INTERFACE(IMyBinderService, data, reply);

            reply->writeInt32(getValue());
			LOGI("Bn: value sended!\n");
			printf("Bn: value sended!\n");
            return 0;
        } break;
		
		default:
            return BBinder::onTransact(code, data, reply, flags);
	}

}


};	//namespace android