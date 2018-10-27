package miscellaneous.app.anrdoffer.anrd_service.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import miscellaneous.app.anrdoffer.utils.L;

/**
 *
 */
public class TestBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        L.logE("TestBroadCastReceiver onReceive");
    }
}
