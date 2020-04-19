package studios.luxurious.igovern.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.robinhood.ticker.TickerView;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.BaseActivity;

public class LaunchActivity extends BaseActivity {

    private TickerView tickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_activity);
        tickerView = findViewById(R.id.ticker1);

        tickerView.setPreferredScrollingDirection(TickerView.ScrollingDirection.ANY);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();

            }
        }, 4000);

    }


    @Override
    protected void onUpdate() {

        String name = (getString(R.string.app_name));
        tickerView.setText(name);
    }


}
