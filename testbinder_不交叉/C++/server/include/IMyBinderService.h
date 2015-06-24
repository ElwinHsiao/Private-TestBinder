#include <binder/IInterface.h>
#include <binder/Parcel.h>

namespace android {

class IMyBinderService: public IInterface
{
public:
    DECLARE_META_INTERFACE(MyBinderService);
	virtual int getValue() = 0;
};

class BnMyBinderService: public BnInterface<IMyBinderService>
{
public:
    virtual status_t    onTransact( uint32_t code,
                                    const Parcel& data,
                                    Parcel* reply,
                                    uint32_t flags = 0);
};


};	//end of namespace android