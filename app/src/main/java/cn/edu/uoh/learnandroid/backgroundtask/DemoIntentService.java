package cn.edu.uoh.learnandroid.backgroundtask;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class DemoIntentService extends IntentService {
    private static final String TAG = "DemoIntentService";
    // 参数key
    static final String EXTRA_PARAM = "EXTRA_PARAM";

    public DemoIntentService() {
        super("DemoIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startAction(Context context, String action, int num) {
        Intent intent = new Intent(context, DemoIntentService.class);
        intent.setAction(action);
        intent.putExtra(EXTRA_PARAM, num);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String action = intent.getAction();
        Log.d(TAG, "onHandleIntent: action = " + action);
        // 获取参数
        int num = intent.getIntExtra(EXTRA_PARAM, 0);
        Log.d(TAG, "onHandleIntent: num = " + num);
    }
}
