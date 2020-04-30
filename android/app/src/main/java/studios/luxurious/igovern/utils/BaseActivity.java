package studios.luxurious.igovern.utils;

import android.os.Handler;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final Random RANDOM = new Random(System.currentTimeMillis());

    private Handler handler = new Handler();
    private boolean resumed;

    @Override
    protected void onResume() {
        super.onResume();
        resumed = true;
        handler.postDelayed(createRunnable(), 1000);
    }

    private Runnable createRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                onUpdate();
                if (resumed) {
                    handler.postDelayed(createRunnable(), RANDOM.nextInt(1750) + 250);
                }
            }
        };
    }

    protected abstract void onUpdate();

    @Override
    protected void onPause() {
        resumed = false;
        super.onPause();
    }

}
