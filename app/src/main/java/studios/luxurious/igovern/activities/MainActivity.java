package studios.luxurious.igovern.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import studios.luxurious.igovern.utils.Constants;
import studios.luxurious.igovern.utils.DBAdapter;


public class MainActivity extends AppCompatActivity {

    private final static int ID_HOME = 1;
    private final static int ID_EXPLORE = 2;
    private final static int ID_NOTIFICATION = 3;

    private CFAlertDialog alertDialog;
    MeowBottomNavigation bottomNavigation;
    String location;


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

        location = "Kefinco, KAKAMEGA";

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment selectedFragment;

                switch (item.getId()) {
                    case ID_HOME:
                        selectedFragment = new HomeFragment();
                        break;
                    case ID_EXPLORE:
                        selectedFragment = new ExploreFragment();
                        break;
                    case ID_NOTIFICATION:
                        selectedFragment = new NotificationsFragment();
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

        bottomNavigation.show(ID_HOME, true);

    }

    @Override
    public void onBackPressed() {

        if (!bottomNavigation.isShowing(ID_HOME)) {
            bottomNavigation.show(ID_HOME, true);
            return;
        }
        super.onBackPressed();
    }

    private void goToSelectedFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

    public void openSendProblemBottomDialog() {
        showReportProblemDialog();
    }

    public void openSendSuggestionBottomDialog() {
        showSendSuggestionDialog();
    }

    public void goToViewHistory() {
        bottomNavigation.show(ID_EXPLORE, true);

    }

    private void showReportProblemDialog() {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MainActivity.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET);
        builder.setCancelable(true);
        builder.setHeaderView(R.layout.bottom_sheet_send_problem);
        alertDialog = builder.show();

        final Button sendBtn = alertDialog.findViewById(R.id.send);
        Button cancelBtn = alertDialog.findViewById(R.id.cancel);
        final EditText subject_editText = alertDialog.findViewById(R.id.subject);
        final EditText message_editText = alertDialog.findViewById(R.id.message);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = message_editText.getText().toString();
                String subject = subject_editText.getText().toString();


                if (subject.length() == 0) {
                    subject_editText.setError("Provide a title");
                    return;
                }


                if (message.length() == 0) {
                    message_editText.setError("Provide a description");
                    return;
                }


                addNewPost(message, subject, location, Constants.PROBLEM_TYPE, 0);

                alertDialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    private void addNewPost(String message, String title, String location, int type, int status) {

        DBAdapter db = new DBAdapter(MainActivity.this);
        db.open();
        db.insertNewPost(title, location, message, status, type);
        db.close();

    }

    private void showSendSuggestionDialog() {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MainActivity.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET);
        builder.setCancelable(true);
        builder.setHeaderView(R.layout.bottom_sheet_send_suggestion);
        alertDialog = builder.show();


        final Button sendBtn = alertDialog.findViewById(R.id.send);
        Button cancelBtn = alertDialog.findViewById(R.id.cancel);
        final EditText title_editText = alertDialog.findViewById(R.id.title);
        final EditText message_editText = alertDialog.findViewById(R.id.message);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = message_editText.getText().toString();
                String title = title_editText.getText().toString();


                if (title.length() == 0) {
                    title_editText.setError("Provide a title");
                    return;
                }

                if (message.length() == 0) {
                    message_editText.setError("Provide a suggestion");
                    return;
                }

                addNewPost(message, title, location, Constants.SUGGESTION_TYPE, 0);

                alertDialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}
