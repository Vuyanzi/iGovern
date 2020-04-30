package studios.luxurious.igovern.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import studios.luxurious.igovern.BuildConfig;
import studios.luxurious.igovern.R;

public class About_us extends AppCompatActivity {

    TextView aboutUsTextViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        aboutUsTextViewTitle = findViewById(R.id.about_title);


        String version_Name = BuildConfig.VERSION_NAME;
        int version_Code = BuildConfig.VERSION_CODE;

        String about_us_title = getString(R.string.app_name)+ " "+ version_Name+ "("+ version_Code+")";

        aboutUsTextViewTitle.setText(about_us_title);
    }
}
