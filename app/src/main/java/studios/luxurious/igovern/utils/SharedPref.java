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


}
