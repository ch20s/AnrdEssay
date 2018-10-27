package miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import miscellaneous.app.anrdoffer.utils.L;

/**
 * @see <a href=https://developer.android.com/guide/components/services?hl=zh-cn>Developer Android 服务</a>
 * 如需让接口跨不同进程的工作，则可使用Messenger为服务创建接口。服务可以这种方式定义对应于不同类型Message对象的Handler。
 * 此Handler是Messenger的基础，后者随后可与客户端分享一个IBinder，从而让客户端能利用Message对象向服务发送命令。
 * 此外还可定义自有Messenger，以便服务回传消息。
 * 如需让服务与远程进程通信，则可使用Messenger为您的服务提供接口。
 * <p>
 * <code>
 * <br>
 * 服务端创建一个handler,由其接收来自客户端的每个调用的回调
 * </br>
 * <br>
 * Handler用于创建Messenger对象(对Handler的引用)
 * </br>
 * <br>
 * Messenger创建一个IBinder，服务通过onBind()使其返回客户端
 * </br>
 * <br>
 * 客户端使用IBinder将Messenger(引用服务的Handler)实例化，然后使用后者将Message对象发送给服务端
 * </br>
 * <br>
 * 服务在其Handler中(在handleMessage()方法中)接收每个Message
 * </br>
 * <p>
 * </code
 * </p>
 */
public class BasicLocalBindMessageService extends Service {
    // 服务端创建一个handler,由其接收来自客户端的每个调用的回调
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 服务在其Handler中(在handleMessage()方法中)接收每个Message
            Bundle b = (Bundle) msg.obj;
            L.logE("handleMessage " + b.getString("src") + ", process id " + android.os.Process.myPid() + " thread name : " + Thread.currentThread().getName());
            Message message = Message.obtain();
            try {
                Thread.sleep(3000);
                Bundle msgBundle = new Bundle();
                msgBundle.putString("src", getClass().getName());
                message.obj = msgBundle;
                msg.replyTo.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    Messenger mMessenger = new Messenger(mHandler);

    /*
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。
     * */
    @Override
    public void onCreate() {
        super.onCreate();
        L.logE(getClass().getName() + " onCreate");
    }

    /*
     * 如果组件通过调用 startService() 启动服务（这会导致对 onStartCommand() 的调用），
     * 则服务将一直运行，直到服务使用 stopSelf() 自行停止运行，
     * 或由其他组件通过调用 stopService() 停止它为止。
     * 应用组件（如 Activity）可以通过调用 startService() 方法并传递 Intent 对象（指定服务并包含待使用服务的所有数据）来启动服务。
     * 服务通过 onStartCommand() 方法接收此 Intent。
     * */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 返回值用于描述系统如何在服务终止的情况下继续运行服务
        String value = intent == null ? "no" : intent.getStringExtra("name");
        L.logE(getClass().getName() + " , " + " onStartCommand, intent == " + intent + " , value " + value);
        return super.onStartCommand(intent, flags, startId);
    }

    /*
     * 如不允许绑定服务，返回null
     * 若允许，以bindService()的方式启动，在此方法的实现中必须通过返回IBinder提供一个接口。
     * 如果组件是通过调用 bindService() 来创建服务（且未调用 onStartCommand()，则服务只会在该组件与其绑定时运行。
     * 一旦该服务与所有客户端之间的绑定全部取消，系统便会销毁它。
     * 有3种方法可以创建IBinder。
     * */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.logE("onBind BasicLocalBindMessageService Thread = "+Thread.currentThread().getName());
        IBinder iBinder = mMessenger.getBinder();
        return iBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        L.logE(getClass().getName() + " onRebind, intent == " + intent);
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.logE(getClass().getName() + " onUnbind, intent == " + intent);
        return super.onUnbind(intent);
    }

    public void clientAccessMessageServiceViaBinder() {
        Toast.makeText(getApplicationContext(), "clientAccessMessageServiceViaBinder", Toast.LENGTH_SHORT).show();
    }

    /*
     * 当服务不再使用且将被销毁时，系统将调用此方法。服务应该实现此方法来清理所有资源，如线程、注册的侦听器、接收器等。
     * 这是服务接收的最后一个调用。
     * */
    @Override
    public void onDestroy() {
        super.onDestroy();
        L.logE(getClass().getName() + " onDestroy");
    }
}
