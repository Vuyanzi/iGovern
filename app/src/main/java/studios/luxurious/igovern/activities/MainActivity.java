package studios.luxurious.igovern.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import studios.luxurious.igovern.R;
import studios.luxurious.igovern.fragments.ExploreFragment;
import studios.luxurious.igovern.fragments.HomeFragment;
import studios.luxurious.igovern.fragments.NotificationsFragment;
import studios.luxurious.igovern.utils.Constants;
import studios.luxurious.igovern.utils.DBAdapter;
import studios.luxurious.igovern.utils.SharedPref;


public class MainActivity extends AppCompatActivity {

    private final static int ID_HOME = 1;
    private final static int ID_EXPLORE = 2;
    private final static int ID_NOTIFICATION = 3;

    private CFAlertDialog alertDialog;
    MeowBottomNavigation bottomNavigation;
    String location;

    ProgressDialog progressDialog;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPref = new SharedPref(this);


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
                        selectedFragment = new HomeFragment(sharedPref.getCountyName());
                        break;
                    case ID_EXPLORE:
                        selectedFragment = new ExploreFragment();
                        break;
                    case ID_NOTIFICATION:
                        selectedFragment = new NotificationsFragment();
                        break;
                    default:
                        selectedFragment = new HomeFragment(sharedPref.getCountyName());

                }
                goToSelectedFragment(selectedFragment);

            }
        });

        goToSelectedFragment(new HomeFragment(sharedPref.getCountyName()));

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

//        bottomNavigation.setCount(ID_NOTIFICATION, "3");
        bottomNavigation.show(ID_HOME, true);
        checkPermissions();

    }

    @Override
    public void onBackPressed() {

        if (!bottomNavigation.isShowing(ID_HOME)) {
            bottomNavigation.show(ID_HOME, true);
            return;
        }
        super.onBackPressed();
    }


    private void checkPermissions() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        boolean[] permissionGranted = new boolean[permissions.length];
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            permissionGranted[i] = ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        if (!permissionGranted[0]) {
            showCFDialog(permissions);
        }

    }

    private void showCFDialog(final String[] permissions) {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MainActivity.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET);

        // Title and message
        builder.setTitle("Grant us permission");
        builder.setMessage("In order to function properly, " + getString(R.string.app_name) + " needs permission to write to your phone storage.");

        builder.setTextGravity(Gravity.CENTER);

        builder.addButton("Allow Access", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
                alertDialog.dismiss();
            }
        });

        builder.setHeaderView(R.layout.dialog_header_permission_layout);
        builder.setCancelable(false);
        alertDialog = builder.show();

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
        final EditText userName_editText = alertDialog.findViewById(R.id.userName);

        userName_editText.setText(sharedPref.getUserName());

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = message_editText.getText().toString();
                String subject = subject_editText.getText().toString();
                String userName = userName_editText.getText().toString();


                if (userName.length() == 0) {
                    userName_editText.setError("Provide a username");
                    return;
                }


                if (subject.length() == 0) {
                    subject_editText.setError("Provide a title");
                    return;
                }


                if (message.length() == 0) {
                    message_editText.setError("Provide a description");
                    return;
                }


                addNewPost(message, subject, location, Constants.PROBLEM_TYPE, 0);
                JsonObject suggestionJson = new JsonObject();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("content", message_editText.getText().toString());
                jsonObject.addProperty("device", Constants.getUniqueDeviceId(MainActivity.this));
                jsonObject.addProperty("county", sharedPref.getCountyName());
                jsonObject.addProperty("title", subject_editText.getText().toString());
                jsonObject.addProperty("type", Constants.PROBLEM_TYPE_STRING);

                suggestionJson.add("suggestion", jsonObject);

                submitData(suggestionJson,alertDialog);
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
        final EditText userName_editText = alertDialog.findViewById(R.id.userName);

        userName_editText.setText(sharedPref.getUserName());

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = message_editText.getText().toString();
                String title = title_editText.getText().toString();
                String userName = userName_editText.getText().toString();



                if (userName.length() == 0) {
                    userName_editText.setError("Provide a username");
                    return;
                }


                if (title.length() == 0) {
                    title_editText.setError("Provide a title");
                    return;
                }

                if (message.length() == 0) {
                    message_editText.setError("Provide a suggestion");
                    return;
                }

                addNewPost(message, title, location, Constants.SUGGESTION_TYPE, 0);

               JsonObject suggestionJson = new JsonObject();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("content", message_editText.getText().toString());
                jsonObject.addProperty("device", Constants.getUniqueDeviceId(MainActivity.this));
                jsonObject.addProperty("county", sharedPref.getCountyName());
                jsonObject.addProperty("title", title_editText.getText().toString());
                jsonObject.addProperty("type", Constants.SUGGESTION_TYPE_STRING);

                suggestionJson.add("suggestion", jsonObject);

                submitData(suggestionJson,alertDialog);

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                checkPermissions();
            }
        }
    }

    private void submitData(JsonObject jsonObject, final CFAlertDialog alertDialog) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Submitting suggestion. Please wait");
        progressDialog.show();
        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<JsonObject> call = service.postData(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    String response_string = response.body().toString();
                    try {
                        JSONObject obj = new JSONObject(response_string);
                        JSONObject jsonObject1 = obj.getJSONObject("data");
                        int id = jsonObject1.getInt("id");

                        showSuccessfulDialog("Successful", "Your message has been sent successfully.");

                    } catch (JSONException e) {
                        e.printStackTrace();
                        showSuccessfulDialog("Failed", "Something went wrong. Please try again");

                    }

                    alertDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                alertDialog.dismiss();

            }

        });
    }

    private interface ApiService {
        @POST("api/suggestions")
        Call<JsonObject> postData(@Body JsonObject body);
    }


    void showSuccessfulDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }


}
