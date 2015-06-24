/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: Z:\\android_src403\\packages\\teststudy\\testbinder\\jclient\\MyBinderClient2\\src\\com\\study\\MyBinderService\\IMyBinderServer.aidl
 */
package com.study.MyBinderService;

public interface IMyBinderServer extends android.os.IInterface {
	/** Local-side IPC implementation stub class. */
	public static abstract class Stub extends android.os.Binder implements
			com.study.MyBinderService.IMyBinderServer {
		private static final java.lang.String DESCRIPTOR = "com.study.MyBinderService.IMyBinderServer";

		/** Construct the stub at attach it to the interface. */
		public Stub() {
			this.attachInterface(this, DESCRIPTOR);
		}

		/**
		 * Cast an IBinder object into an
		 * com.study.MyBinderService.IMyBinderServer interface, generating a
		 * proxy if needed.
		 */
		public static com.study.MyBinderService.IMyBinderServer asInterface(
				android.os.IBinder obj) {
			if ((obj == null)) {
				return null;
			}
			android.os.IInterface iin = (android.os.IInterface) obj
					.queryLocalInterface(DESCRIPTOR);
			if (((iin != null) && (iin instanceof com.study.MyBinderService.IMyBinderServer))) {
				return ((com.study.MyBinderService.IMyBinderServer) iin);
			}
			return new com.study.MyBinderService.IMyBinderServer.Stub.Proxy(obj);
		}

		public android.os.IBinder asBinder() {
			return this;
		}

		@Override
		public boolean onTransact(int code, android.os.Parcel data,
				android.os.Parcel reply, int flags)
				throws android.os.RemoteException {
			switch (code) {
			case INTERFACE_TRANSACTION: {
				reply.writeString(DESCRIPTOR);
				return true;
			}
			case TRANSACTION_getValue: {
				data.enforceInterface(DESCRIPTOR);
				int _result = this.getValue();
				reply.writeNoException();
				reply.writeInt(_result);
				return true;
			}
			}
			return super.onTransact(code, data, reply, flags);
		}

		private static class Proxy implements
				com.study.MyBinderService.IMyBinderServer {
			private android.os.IBinder mRemote;

			Proxy(android.os.IBinder remote) {
				mRemote = remote;
			}

			public android.os.IBinder asBinder() {
				return mRemote;
			}

			public java.lang.String getInterfaceDescriptor() {
				return DESCRIPTOR;
			}

			public int getValue() throws android.os.RemoteException {
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				int _result;
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					mRemote.transact(Stub.TRANSACTION_getValue, _data, _reply,
							0);
					_reply.readException();
					_result = _reply.readInt();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
				return _result;
			}
		}

		static final int TRANSACTION_getValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
	}

	public int getValue() throws android.os.RemoteException;
}
