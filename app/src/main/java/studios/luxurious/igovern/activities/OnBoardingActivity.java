package studios.luxurious.igovern.activities;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.SharedPref;

public class OnBoardingActivity extends AhoyOnboarderActivity {

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(this);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Report problems to your government", "", R.drawable.backpack);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Send suggestions to your leaders", "", R.drawable.chalk);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Check on progress", "", R.drawable.chat);
        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Build your nation", "The power to make change is now a button away.", R.drawable.ic_gavel);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
            //page.setTitleTextSize(dpToPixels(12, this));
            //page.setDescriptionTextSize(dpToPixels(8, this));
            page.setIconLayoutParams(180, 180, 100, 10, 10, 10);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(true);
        setGradientBackground();

        //set the button style you created
        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        setFont(face);

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        sharedPref.setIsFirstTime(false);
        if (sharedPref.getCountyName() != null){
            startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
        }else {
            startActivity(new Intent(OnBoardingActivity.this, MapsActivity.class));
        }
        finish();
    }
}
