package miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.List;

import miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.BasicLocalBindLocalService;
import miscellaneous.app.anrdoffer.utils.L;

/**
 *
 */
public class LocalServiceConnection implements ServiceConnection {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        L.logE(getClass().getName() + " onServiceConnected");
        BasicLocalBindLocalService.LocalBinder binder = (BasicLocalBindLocalService.LocalBinder) service;
        BasicLocalBindLocalService basicLocalBindLocalService = binder.getBasicLocalBindLocalService();
        basicLocalBindLocalService.clientAccessServiceViaBinder();
        List<String> data = binder.getInitDataWhenBound();
        for (String s : data) {
            L.logE(s);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
