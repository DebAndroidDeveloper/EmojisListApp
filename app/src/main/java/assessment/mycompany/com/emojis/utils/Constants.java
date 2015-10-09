package assessment.mycompany.com.emojis.utils;

import assessment.mycompany.com.emojis.system.network.EmojisApiManager;

/**
 * Created by Debasis on 10/07/2015.
 */
public class Constants {
    public interface IntentExtras {
        String ERROR_NO_NETWORK = "com.magnet.emojis.appIntentExtras.ERROR_NO_NETWORK";
        String MESSAGE = "com.magnet.emojis.appIntentExtras.MESSAGE";
        String JSON_RESPONSE = "com.magnet.emojis.appIntentExtras.JSON_RESPONSE";
        String REQUEST_ID = "com.magnet.emojis.appIntentExtras.ID";
    }

    public interface IntentActions {
        String EMOJIS_DETAILS = EmojisApiManager.class.getName();
        String ACTION_ERROR = "com.magnet.emojis.appIntentExtras.ACTION_ERROR";
        String ACTION_SUCCESS = "com.magnet.emojis.appIntentExtras.ACTION_SUCCESS";
    }

    public interface ApiMethods {

    }

    public interface JsonKeys {
        String ERRORS = "errors";
        String MESSAGE = "message";
    }

    public interface ApiRequestId {
        int API_BASE_VALUE = 200;
        int GET_EMOJIS_DATA = API_BASE_VALUE + 1;

    }
}
