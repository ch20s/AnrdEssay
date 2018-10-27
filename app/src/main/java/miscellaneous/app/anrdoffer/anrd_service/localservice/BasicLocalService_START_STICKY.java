package miscellaneous.app.anrdoffer.anrd_service.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
 * 通过startService()方式启动的服务，一旦启动将在后台无限期运行。在执行特定任务后将自动停止运行，但并不是销毁。
 * </p>
 */
public class BasicLocalService_START_STICKY extends BasicLocalService {

    /*
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。
     * */
    @Override
    public void onCreate() {
        super.onCreate();
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
        /*
        * START_STICKY
        * 如果系统在 onStartCommand() 返回后终止服务，则会重建服务并调用 onStartCommand()，但不会重新传递最后一个 Intent。
        * 相反，除非有挂起 Intent 要启动服务（在这种情况下，将传递这些 Intent ），否则系统会通过空 Intent 调用 onStartCommand()。
        * 这适用于不执行命令、但无限期运行并等待作业的媒体播放器（或类似服务）。
        * */
        // todo 尝试使用多个耗时Intent依次启动服务进行验证
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    int getStartMode() {
        return START_STICKY;
    }

    /*
     * 如不允许绑定服务，返回null
     * 若允许，以bindService()的方式启动，在此方法的实现中必须通过返回IBinder提供一个接口。
     * 如果组件是通过调用 bindService() 来创建服务（且未调用 onStartCommand()，则服务只会在该组件与其绑定时运行。
     * 一旦该服务与所有客户端之间的绑定全部取消，系统便会销毁它。
     * */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // cpu密集型或阻塞操作应新启动线程
    private void cpuboundOrBlocking() {

    }

    /*
     * 当服务不再使用且将被销毁时，系统将调用此方法。服务应该实现此方法来清理所有资源，如线程、注册的侦听器、接收器等。
     * 这是服务接收的最后一个调用。
     * */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
