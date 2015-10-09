package assessment.mycompany.com.emojis.system.network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import assessment.mycompany.com.emojis.utils.CommonUtils;
import assessment.mycompany.com.emojis.utils.Constants;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ApiIntentService extends IntentService {

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void getEmojisData(Context context) {
        Intent getIntent = new Intent();
        getIntent.setAction(Constants.IntentActions.EMOJIS_DETAILS);
        getIntent.putExtra(Constants.IntentExtras.REQUEST_ID, Constants.ApiRequestId.GET_EMOJIS_DATA);
        startApiService(context, getIntent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */


    public ApiIntentService() {
        super("ApiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            NetworkManager networkManager = ApiManagerFactory.createApiManager(action, this);
            networkManager.logExecute(networkManager.getClass().getCanonicalName(), intent.getIntExtra(Constants.IntentExtras.REQUEST_ID, -1));
            networkManager.execute(intent);
        }
    }

    private static boolean startApiService(Context context, Intent intent) {
        if (CommonUtils.isNetworkAvailable(context)) {
            intent.setClass(context, ApiIntentService.class);
            context.startService(intent);
            return true;
        } else {
            Intent errIntent = new Intent();
            errIntent.setAction(Constants.IntentActions.ACTION_ERROR);
            errIntent.putExtra(Constants.IntentExtras.MESSAGE, Constants.IntentExtras.ERROR_NO_NETWORK);
            context.sendBroadcast(intent);
            return false;
        }
    }

}
