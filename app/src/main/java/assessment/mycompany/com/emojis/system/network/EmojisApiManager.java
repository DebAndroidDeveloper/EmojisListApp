package assessment.mycompany.com.emojis.system.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import assessment.mycompany.com.emojis.BuildConfig;
import assessment.mycompany.com.emojis.system.ApiException;
import assessment.mycompany.com.emojis.utils.Constants;

/**
 * Created by Debasis on 10/07/2015.
 */
public class EmojisApiManager extends NetworkManager {
    private int mRequestId = -1;

    public EmojisApiManager(Context currentContext) {
        mContext = currentContext;
    }

    @Override
    public void execute(Intent intent) {
        mRequestId = intent.getIntExtra(Constants.IntentExtras.REQUEST_ID, -1);

        String url = BuildConfig.BASE_EMOJIS_API_URL ;

        try {
            String response = get(url);
            JSONObject jsonObject = new JSONObject(response);
            Log.d(getTag(),"HTTP response " + jsonObject.toString(4));
            broadcast(Constants.IntentActions.ACTION_SUCCESS, response, null);
        } catch (IOException e) {
            Log.e(getTag(), getTag() + " IOException " + e.getMessage());
            broadcast(Constants.IntentActions.ACTION_ERROR, getTag() + " " + e.getMessage(), null);
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(getTag(), getTag() + " JSONException " + e.getMessage());
            broadcast(Constants.IntentActions.ACTION_ERROR, getTag() + " " + e.getMessage(), null);
            e.printStackTrace();
        } catch (ApiException e) {
            Log.e(getTag(), getTag() + " Api Exception " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public int getRequestId() {
        return mRequestId;
    }

    @Override
    public String getTag() {
        return EmojisApiManager.class.getCanonicalName();
    }
}
