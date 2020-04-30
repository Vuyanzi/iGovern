package studios.luxurious.igovern.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private Context ctx;
    private SharedPreferences default_prefence;

    public SharedPref(Context context) {
        this.ctx = context;
        default_prefence = context.getSharedPreferences("iGovern", Context.MODE_PRIVATE);
    }


    public void setCountyName(String county) {
        default_prefence.edit().putString("county", county).apply();
    }

    public String getCountyName() {
        return default_prefence.getString("county", null);
    }


    public void setUserName(String username) {
        default_prefence.edit().putString("username", username).apply();
    }

    public String getUserName() {
        return default_prefence.getString("username", null);
    }

    public void setIsFirstTime(boolean isFirstTime) {
        default_prefence.edit().putBoolean("isFirstTime", isFirstTime).apply();
    }

    public boolean isFirstTime() {
        return default_prefence.getBoolean("isFirstTime", true);
    }


}
