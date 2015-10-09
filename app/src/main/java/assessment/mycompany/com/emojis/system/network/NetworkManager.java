package assessment.mycompany.com.emojis.system.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import assessment.mycompany.com.emojis.system.ApiException;
import assessment.mycompany.com.emojis.utils.Constants;

/**
 * Created by Debasis on 10/07/2015.
 */
public abstract class NetworkManager {
    public static final MediaType JSON = MediaType.parse("application/json");

    protected Context mContext;

    public abstract void execute(Intent intent);

    public abstract int getRequestId();

    public abstract String getTag();

    public void logExecute(String tag, int requestId) {
        Log.d(tag, "-_-_-_-_-_-_ " + tag + " calling request id " + requestId + " -_-_-_-_-_-_");
    }

    protected String get(String url) throws IOException, ApiException, JSONException {

        String responseString = "";


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        responseString = response.body().string();

        if (!response.isSuccessful()) {
            handleError(response.code(), request.uri() + "", responseString);
        }

        return responseString;
    }

    private void handleError(int responseCode, String uri, String responseString) throws JSONException, ApiException {
        Log.e(getTag(), getTag() + " Error executing " + uri + " \nresponse code " + responseCode + " \nMessage: " + responseString);

        broadcast(Constants.IntentActions.ACTION_ERROR, getTag() + " <p><b>Error executing:</b> " + uri + " </p><p><b>Response code:</b> " + responseCode + " </p><b>Message:</b> " + responseString, null);

        throw new ApiException("Response Code: " + responseCode + " Message : " + responseString);
    }

    /**
     * Util method to broadcast the result
     *
     * @param action
     * @param message
     * @param jsonResponse
     */
    protected void broadcast(String action, String message, JSONObject jsonResponse) {

        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(Constants.IntentExtras.MESSAGE, message);

        if (jsonResponse != null)
            intent.putExtra(Constants.IntentExtras.JSON_RESPONSE, jsonResponse.toString());

        intent.putExtra(Constants.IntentExtras.REQUEST_ID, getRequestId());
        mContext.sendBroadcast(intent);
    }


}
