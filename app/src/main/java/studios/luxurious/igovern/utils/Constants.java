package studios.luxurious.igovern.utils;

import android.content.Context;
import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    public final static int PROBLEM_TYPE = 1;
    public final static int SUGGESTION_TYPE = 2;

    public final static String BASE_URL = "https://igovern.herokuapp.com/";

    public static String getUniqueDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
    }

    public final static String SUGGESTION_TYPE_STRING = "suggestion";
    public final static String PROBLEM_TYPE_STRING = "problem";

    public static String getAssignedId(int id){
        return "iGN"+id+"20";
    }

    public static String getDate(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String currentDateTime = dateFormat.format(new Date());
            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
