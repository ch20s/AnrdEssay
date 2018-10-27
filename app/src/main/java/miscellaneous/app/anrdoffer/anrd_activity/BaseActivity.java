package miscellaneous.app.anrdoffer.anrd_activity;

import android.app.Activity;
import android.view.View;

/**
 *
 */
public class BaseActivity extends Activity implements View.OnClickListener{
    public void initClickListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
