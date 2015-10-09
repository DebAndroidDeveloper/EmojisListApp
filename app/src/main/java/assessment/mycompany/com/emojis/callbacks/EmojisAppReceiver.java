package assessment.mycompany.com.emojis.callbacks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import assessment.mycompany.com.emojis.utils.Constants;

public class EmojisAppReceiver extends BroadcastReceiver {
    private ApiCallBack apiCallBack;

    public EmojisAppReceiver(ApiCallBack apiCallBack) {
        this.apiCallBack = apiCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.IntentActions.ACTION_ERROR)) {
            apiCallBack.onHttpResponseError(intent);
        } else if (intent.getAction().equals(Constants.IntentActions.ACTION_SUCCESS)) {
            apiCallBack.onHttpRequestComplete(intent);
        }
    }
}
