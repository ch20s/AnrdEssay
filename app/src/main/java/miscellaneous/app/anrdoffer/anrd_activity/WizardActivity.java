package miscellaneous.app.anrdoffer.anrd_activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import miscellaneous.app.anrdoffer.R;
import miscellaneous.app.anrdoffer.anrd_service.localservice.BasicLocalService;
import miscellaneous.app.anrdoffer.anrd_service.localservice.BasicLocalService_START_NOT_STICKY;
import miscellaneous.app.anrdoffer.anrd_service.localservice.BasicLocalService_START_REDELIVER_INTENT;
import miscellaneous.app.anrdoffer.anrd_service.localservice.BasicLocalService_START_STICKY;
import miscellaneous.app.anrdoffer.anrd_service.test.TestBroadCastReceiver;
import miscellaneous.app.anrdoffer.utils.L;

/**
 *
 */
public class WizardActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wizardlayout);
        initClickListener(R.id.bt_basic_intent_service);
        initClickListener(R.id.bt_start_not_sticky_service);
        initClickListener(R.id.bt_start_sticky_service);
        initClickListener(R.id.bt_start_redelivery_intent_service);

    }



    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.bt_basic_intent_service:
                intent = new Intent(this,BasicLocalService.class);
                intent.putExtra("name", "value");
                startService(intent);
                break;
            case R.id.bt_start_not_sticky_service:
                intent = new Intent(this,BasicLocalService_START_NOT_STICKY.class);
                intent.putExtra("name", "value");
                startService(intent);
                break;
            case R.id.bt_start_sticky_service:
                intent = new Intent(this,BasicLocalService_START_STICKY.class);
                intent.putExtra("name", "value");
                startService(intent);
                break;
            case R.id.bt_start_redelivery_intent_service:
                intent = new Intent(this,BasicLocalService_START_REDELIVER_INTENT.class);
                intent.putExtra("name", "value");
                startService(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.logE(getClass().getName() + " onDestroy");
    }
}
