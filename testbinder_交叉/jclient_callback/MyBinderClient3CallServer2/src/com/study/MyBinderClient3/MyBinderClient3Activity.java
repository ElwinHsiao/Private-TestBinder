package com.study.MyBinderClient3;

import android.app.Activity;
import android.os.Binder;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyBinderClient3Activity extends Activity {
    private static final String TAG = "xxxxxxxxxxxxxx-MyBinderClient3Activity-xxxxxxxxxxxxxxxx";
	private TextView mLable;
	private Button mIntButton, mStrButton;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        mLable = (TextView) findViewById(R.id.text1);
        mIntButton = (Button) findViewById(R.id.get_int);
        mStrButton = (Button) findViewById(R.id.get_string);
        
        mRemote = ServiceManager.getService("MyBinderServer2");

        mIntButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				mLable.setText("value is: " + (Integer)getValue(GET_INT));
			}
		});

        mStrButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mLable.setText("value is: " + getValue(GET_STRING));
			}
		});
        
        Button callBack = (Button) findViewById(R.id.call_back);
        callBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//				new Thread(new Runnable() {
//					public void run() {
//						getValue(CALL_BACK);
//					}
//				}).start();
				getValue(CALL_BACK);
				
//				mLable.setText("call is going");
			}
		});
        
//        new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				Looper.prepare();
//				// TODO Auto-generated method stub
//				Toast.makeText(MyBinderClient3Activity.this, "myserver2 call java application", Toast.LENGTH_SHORT).show();
//				Toast.makeText(MyBinderClient3Activity.this, "myserver2 call java application", Toast.LENGTH_SHORT).show();
//				Toast.makeText(MyBinderClient3Activity.this, "myserver2 call java application", Toast.LENGTH_LONG).show();
//				Looper.loop();
//			}
//		}).start();

    }
    
	private android.os.IBinder mRemote;
	private static final int GET_INT = 1;
	private static final int GET_STRING = 2;
	private static final int CALL_BACK = 3;
//	private static final java.lang.String DESCRIPTOR = "test.study.IMyBinderService";

//    public int getValue() throws android.os.RemoteException {
//		android.os.Parcel _data = android.os.Parcel.obtain();
//		android.os.Parcel _reply = android.os.Parcel.obtain();
//		int _result;
//		try {
////			_data.writeInterfaceToken(DESCRIPTOR);
//			mRemote.transact(TRANSACTION_getValue, _data, _reply, 0);
////			_reply.readException();
//			//_result = _reply.readInt();
//			_result = _reply.readInt();
//		} finally {
//			_reply.recycle();
//			_data.recycle();
//		}
//		return _result;
//	}
	
	private Object getValue(int code) {
		Object result = null;
		
		android.os.Parcel data = null;
		android.os.Parcel reply = null;
        try {
    		data = android.os.Parcel.obtain();
    		reply = android.os.Parcel.obtain();
    		
    		switch(code) {
    		case GET_INT:
        		mRemote.transact(code, data, reply, 0);
    			result = new Integer(reply.readInt());
    			break;
    		case GET_STRING:
        		mRemote.transact(code, data, reply, 0);
    			result = reply.readString();
    			break;
    		case CALL_BACK:
    			data.writeStrongBinder(mMyjavaBBinder);
        		mRemote.transact(code, data, reply, 0);
//    			result = reply.readString();
    			break;
    		default:
    			Log.e(TAG, "invalid code!");
    			break;
    		}
    		    		
		} catch (RemoteException e) {
			e.printStackTrace();
		} finally {
			if (data != null ) {
    			data.recycle();						
			}
			if (reply != null ) {
    			reply.recycle();
			}
		}
        
		return result;        
	}
	
	private MyjavaBBinder mMyjavaBBinder = new MyjavaBBinder();
    private class MyjavaBBinder extends Binder {
    	
		public boolean onTransact(int code, android.os.Parcel data,
				android.os.Parcel reply, int flags)
				throws android.os.RemoteException {
    		Log.d(TAG, "in MyjavaBBinder.onTransact, code=" + code);
    		MyBinderClient3Activity.this.setTitle("myserver2 call java application!");
    		Toast.makeText(MyBinderClient3Activity.this, "myserver2 call java application!", Toast.LENGTH_SHORT).show();
    		
    		
    		mLable.setText("myserver2 call java application!"+ " threadid=" + Thread.currentThread().getId());
    		
    		return super.onTransact(code, data, reply, flags);
    	}
    }
}