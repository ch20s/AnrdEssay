package miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Map;

import miscellaneous.app.anrdoffer.IFpAidlInterface;
import miscellaneous.app.anrdoffer.bean.I_UserBean_AidlInterface;
import miscellaneous.app.anrdoffer.utils.L;

/**
 * @see <a href=https://developer.android.com/guide/components/services?hl=zh-cn>Developer Android 服务</a>
 * <p>
 * 组件可以绑定到服务，与之进行交互。
 * </p>
 * <p>
 * 服务可以用于执行进程间通讯。
 * </p>
 * <p>
 * 通过清单文件可将服务声明为私有服务，并阻止其他应用访问。
 * </p>
 * <p>
 * 当应用组件通过调用 bindService() 绑定到服务时，服务即处于“绑定”状态。
 * 绑定服务提供了一个客户端-服务器接口，允许组件与服务进行交互、发送请求、获取结果，甚至是利用进程间通信 (IPC) 跨进程执行这些操作。
 * 仅当与另一个应用组件绑定时，绑定服务才会运行。
 * 多个组件可以同时绑定到该服务，但全部取消绑定后，该服务即会被销毁。
 * </p>
 * <p>
 *     只有允许不同应用的客户端用IPC方式访问服务，且想要在服务中处理多线程时，才有必要使用aidl。
 * </p>
 */
public class BasicLocalBindAidlService extends Service {

    int mInt;

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
        L.logE("onBind Thread == " + Thread.currentThread().getName());
        return mBinder;
    }

    IFpAidlInterface.Stub mBinder = new IFpAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getPid() throws RemoteException {

            return android.os.Process.myPid();
        }

        @Override
        public int getUserId() throws RemoteException {
            return 0xffff;
        }


        @Override
        public void setUser(I_UserBean_AidlInterface user,Map map) throws RemoteException {

            L.logE("user aint " + user.getaInt());
            map.put(mInt++ + "", "map_value");
            map.put("ice", "fire");
            L.logE("getPid Thread == " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

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
