#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>

#include <unistd.h>

using namespace android;

enum 
{
	GET_INT = 1,
	GET_STRING,
	CALL_BACK
};

class MyBinderServer2 : public BBinder
{
	virtual status_t    onTransact( uint32_t code,
                                    const Parcel& data,
                                    Parcel* reply,
                                    uint32_t flags = 0)
	{
		printf("receice request!\n");
		
		switch(code)
		{
			case GET_INT: 
			{
				printf("Bn: b writeInt32 value sended!\n");
				reply->writeInt32(22);
				printf("Bn: a writeInt32 value sended!\n");
				break;
			}
			case GET_STRING: 
			{
				printf("Bn: b writeString16 value sended!\n");
				reply->writeString16(String16("hello from server"));
				printf("Bn: a writeString16 value sended!\n");
				break;
			}
			case CALL_BACK: 
			{
				printf("Bn: b receive callback\n");
				sp<IBinder> bind = data.readStrongBinder();
				printf("Bn: a receive callback\n");
				
				printf("Bn: b in onTransact(!!) excute callback\n");				
				Parcel data1, reply1;				
				bind->transact(77, data1, &reply1);
				printf("Bn: a in onTransact(!!) excute callback\n");
				break;
			}
			default:
			{
				printf("invalid code!\n");
				break;
			}
		}
	
		return 0;
	}

};

int main(int argc, char** argv)
{
    sp<ProcessState> proc(ProcessState::self());
    defaultServiceManager()->addService(
            String16("MyBinderServer2"), new MyBinderServer2());
    ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool();
}