package studios.luxurious.igovern.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.fragments.ExploreFragment;
import studios.luxurious.igovern.fragments.HomeFragment;
import studios.luxurious.igovern.fragments.NotificationsFragment;


public class MainActivity extends AppCompatActivity {

    private final static int ID_HOME = 1;
    private final static int ID_EXPLORE = 2;
    private final static int ID_NOTIFICATION = 3;

    private CFAlertDialog alertDialog;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_EXPLORE, R.drawable.ic_explore));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notification));

        getSupportActionBar().setElevation(0);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment selectedFragment;

                switch (item.getId()) {
                    case ID_HOME:
                        selectedFragment = new HomeFragment();
                        break;
                    case ID_EXPLORE: selectedFragment = new ExploreFragment();
                        break;
                    case ID_NOTIFICATION: selectedFragment = new NotificationsFragment();
                        break;
                    default:
                        selectedFragment = new HomeFragment();

                }

                goToSelectedFragment(selectedFragment);


            }
        });

        goToSelectedFragment(new HomeFragment());

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });


        bottomNavigation.setCount(ID_NOTIFICATION, "3");

        bottomNavigation.show(ID_HOME,true);

    }


    private void goToSelectedFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

    public void openSendProblemBottomDialog(){
        showReportProblemDialog();
    }

    public void openSendSuggestionBottomDialog(){
        showSendSuggestionDialog();
    }

    public void goToViewHistory(){
        bottomNavigation.show(ID_EXPLORE,true);

    }

    private void showReportProblemDialog() {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MainActivity.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET);
        builder.setCancelable(true);
        builder.setHeaderView(R.layout.bottom_sheet_send_problem);
        alertDialog = builder.show();

        Button sendBtn = alertDialog.findViewById(R.id.send);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

    }

    private void showSendSuggestionDialog() {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MainActivity.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET);
        builder.setCancelable(true);
        builder.setHeaderView(R.layout.bottom_sheet_send_suggestion);
        alertDialog = builder.show();

        Button sendBtn = alertDialog.findViewById(R.id.send);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

    }
}
