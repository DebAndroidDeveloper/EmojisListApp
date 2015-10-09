package assessment.mycompany.com.emojis.system.network;

import android.content.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Debasis on 10/07/2015.
 */
public class ApiManagerFactory {

    private ApiManagerFactory() {

    }

    public static NetworkManager createApiManager(String action, Context context) {
        NetworkManager networkManager = null;
        try {
            Constructor<?> c = Class.forName(action).getConstructor(Context.class);
            networkManager = (NetworkManager) c.newInstance(context);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return networkManager;
    }
}
