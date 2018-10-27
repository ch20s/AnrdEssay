package miscellaneous.app.anrdoffer.anrd_activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import miscellaneous.app.anrdoffer.R;
import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.BasicLocalBindAidlService;
import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.BasicLocalBindLocalService;
import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.BasicLocalBindMessageService;
import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.connection.AidlServiceConnection;
import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.connection.LocalServiceConnection;
import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.connection.MessengerServiceConnection;
import miscellaneous.app.anrdoffer.bean.I_UserBean_AidlInterface;
import miscellaneous.app.anrdoffer.utils.L;

/**
 *
 */
public class BinderServiceActivity extends BaseActivity {
    MessengerServiceConnection connection;
    AidlServiceConnection mAidlServiceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binderservice_layout);
        initClickListener(R.id.bt_local_binder_service);
        initClickListener(R.id.bt_message_binder_service);
        initClickListener(R.id.bt_aidl_remote_service);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_local_binder_service:
                Intent localIntent = new Intent(this, BasicLocalBindLocalService.class);
                bindService(localIntent, new LocalServiceConnection(), BIND_AUTO_CREATE);
                break;
            case R.id.bt_message_binder_service:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bundle b = new Bundle();
                        b.putString("src", getClass().getName() + Thread.currentThread().getName());
                        connection.sendMsg(b);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bundle b = new Bundle();
                        b.putString("src", getClass().getName() + Thread.currentThread().getName());
                        connection.sendMsg(b);
                    }
                }).start();
                break;
            case R.id.bt_aidl_remote_service:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        L.logE("light_sub " + Thread.currentThread().getName());
                        int pid = mAidlServiceConnection.getPid();
                        I_UserBean_AidlInterface user = new I_UserBean_AidlInterface();
                        user.setaInt(0x10);
                        mAidlServiceConnection.setUser(user);
                        L.logE(mAidlServiceConnection.getUserId()+"");
                        L.logE(android.os.Process.myPid() + " - " + pid);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        L.logE("dark_sub " + Thread.currentThread().getName());
                        int pid = mAidlServiceConnection.getPid();
                        I_UserBean_AidlInterface user = new I_UserBean_AidlInterface();
                        user.setaInt(0x10);
                        mAidlServiceConnection.setUser(user);
                        L.logE(mAidlServiceConnection.getUserId()+"");
                        L.logE(android.os.Process.myPid() + " - " + pid);
                    }
                }).start();


                break;
        }
    }

    private void bindService() {
        Intent messageIntent = new Intent(BinderServiceActivity.this, BasicLocalBindMessageService.class);
        connection = new MessengerServiceConnection();
        bindService(messageIntent, connection, BIND_AUTO_CREATE);
    }

    private void bindAidlService() {
        Intent aidlIntent = new Intent(this, BasicLocalBindAidlService.class);
        mAidlServiceConnection = new AidlServiceConnection();
        bindService(aidlIntent, mAidlServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        bindMessengerService();
        bindAidlService();
    }

    private void bindMessengerService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                bindService();
                Looper.loop();
            }
        },"A Custom Thread").start();
    }

}
