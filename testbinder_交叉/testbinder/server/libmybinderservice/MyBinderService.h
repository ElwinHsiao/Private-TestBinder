#include "IMyBinderService.h"

namespace android {

class MyBinderService : public BnMyBinderService
{
	public:
		static void instantiate();
		virtual int getValue();
};

};	//namespace android