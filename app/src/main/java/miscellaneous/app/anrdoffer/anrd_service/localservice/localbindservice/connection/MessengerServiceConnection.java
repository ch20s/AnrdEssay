package miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.util.List;

import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.BasicLocalBindLocalService;
import miscellaneous.app.anrdoffer.utils.L;

/**
 *
 */
public class MessengerServiceConnection implements ServiceConnection {

    Messenger mMessenger;
    Messenger mServiceCallBackMessenger;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle b = (Bundle) msg.obj;
            L.logE(getClass().getName() + " handleMessage from service " + b.getString("src") + ", process id " + android.os.Process.myPid() + " thread name : " +Thread.currentThread().getName());
        }
    };


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mMessenger = new Messenger(service);
        L.logE("mMessenger MessengerServiceConnection = "+mMessenger.toString());
        mServiceCallBackMessenger = new Messenger(mHandler);
        L.logE(getClass().getName() + " mMessenger null " + (mMessenger == null));
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public void sendMsg(Bundle msg) {
        L.logE(getClass().getName() + " mMessenger null when sendMsg " + (mMessenger == null));
        Message m = Message.obtain(mHandler);
        m.obj = msg;
        m.replyTo = mServiceCallBackMessenger;
        if (null != mMessenger) {
            try {
                mMessenger.send(m);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
