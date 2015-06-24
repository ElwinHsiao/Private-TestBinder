package com.study.MyBinderService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.study.MyBinderService.IMyBinderServer;

public class MyBinderServer extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	private final IMyBinderServer.Stub mBinder = new IMyBinderServer.Stub() {
		@Override
		public int getValue() throws RemoteException {
			return 66;
		}		
	};
	
//	public class MyBinderServerImpl extends IMyBinderServer.Stub {
//
//		@Override
//		public String getHello(int param) throws RemoteException {
//			return "Hello from server";
//		}
//		
//	}
}
