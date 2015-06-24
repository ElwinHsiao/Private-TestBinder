package com.study.MyBinderClient2;

import com.study.MyBinderService.IMyBinderServer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.IBinder;

public class MyBinderClient2Activity extends Activity {
    private TextView tv;
    private IMyBinderServer mIMyBinderServer;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.text1);
        tv.setFocusable(true);
        tv.setClickable(true);
        
        IBinder binder = ServiceManager.getService("com.study.MyBinderService.IMyBinderServer");
        mIMyBinderServer = IMyBinderServer.Stub.asInterface(binder);
        int iTmp = 0;
        try {
			iTmp = mIMyBinderServer.getValue();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tv.setText(iTmp);

    }
}