package miscellaneous.app.anrdoffer.anrd_service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * 这使用工作线程逐一处理所有启动请求。如果您不要求服务同时处理多个请求，这是最好的选择。
 */
public class BasicIntentService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BasicIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
