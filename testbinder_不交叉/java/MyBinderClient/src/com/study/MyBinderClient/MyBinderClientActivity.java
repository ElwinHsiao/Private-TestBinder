package com.study.MyBinderClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.study.MyBinderService.IMyBinderServer;

public class MyBinderClientActivity extends Activity {
    private TextView tv;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.text1);
        Intent intent = new Intent("com.study.MyBinderService.acton");
        //!!!请注意，绑定需要一段时间，不能马上使mServiceConn
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
        
        tv.setFocusable(true);
        tv.setClickable(true);
        tv.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(isBind) {
			        int tmp = 0;
					try {
						tmp = mIMyBinderServer.getValue();
					} catch (RemoteException e) {
						e.printStackTrace();
					}		
					Toast.makeText(MyBinderClientActivity.this, "getValue():" + tmp, Toast.LENGTH_LONG).show();
				}
			}
		});
        tv.setSelected(true);        
    }
    
    IMyBinderServer mIMyBinderServer;
	protected boolean isBind = false;
	private ServiceConnection mServiceConn = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIMyBinderServer = IMyBinderServer.Stub.asInterface(service);
			if (mIMyBinderServer == null) {
				Toast.makeText(MyBinderClientActivity.this, "binded failed", Toast.LENGTH_LONG).show();
				return;
			}
			isBind  = true;
			
			Toast.makeText(MyBinderClientActivity.this, "binded sucess", Toast.LENGTH_LONG).show();
	        int tmp = 0;
			try {
				tmp = mIMyBinderServer.getValue();
			} catch (RemoteException e) {
				e.printStackTrace();
			}		
	        tv.setText("" + tmp);
		}

		public void onServiceDisconnected(ComponentName name) {
			mIMyBinderServer = null;
		}
	};

	public void onDestory() {
		super.onDestroy();
		unbindService(mServiceConn);
	}
}