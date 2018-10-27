package miscellaneous.app.anrdoffer.anrd_service.localservice.localbindservice.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import miscellaneous.app.anrdoffer.IFpAidlInterface;
import miscellaneous.app.anrdoffer.bean.I_UserBean_AidlInterface;
import miscellaneous.app.anrdoffer.utils.L;

/**
 *
 */
public class AidlServiceConnection implements ServiceConnection {

    IFpAidlInterface mIFpAidlInterface;
    HashMap<String, String> mMap = new HashMap<>();

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mIFpAidlInterface = IFpAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public int getPid() {
        try {
            return mIFpAidlInterface.getPid();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void setUser(I_UserBean_AidlInterface user) {
        try {
            mIFpAidlInterface.setUser(user, mMap);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Set<String> set = mMap.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            L.logE("map key " + it.next());
        }
        L.logE("running");
    }

    public int getUserId() {
        try {
            return mIFpAidlInterface.getUserId();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
