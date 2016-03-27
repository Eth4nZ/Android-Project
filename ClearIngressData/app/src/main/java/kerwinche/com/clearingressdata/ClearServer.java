package kerwinche.com.clearingressdata;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by x on 3/27/16.
 */
public class ClearServer extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ClearServer(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
