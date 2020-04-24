package studios.luxurious.igovern.utils;

import android.content.Context;
import android.provider.Settings;

public class Constants {

    public final static int PROBLEM_TYPE = 1;
    public final static int SUGGESTION_TYPE = 2;

    public final static String BASE_URL = "https://igovern.herokuapp.com/";

    public static String getUniqueDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
    }

    public final static String SUGGESTION_TYPE_STRING = "suggestion";
    public final static String PROBLEM_TYPE_STRING = "problem";

}
