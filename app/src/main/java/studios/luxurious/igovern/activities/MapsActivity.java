package studios.luxurious.igovern.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.SharedPref;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    SharedPref sharedPref;
    private CFAlertDialog alertDialog;

    SmoothProgressBar smoothProgressBar;
    TextView messageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        sharedPref = new SharedPref(this);

        smoothProgressBar = findViewById(R.id.smoothProgressBar);
        messageTv = findViewById(R.id.message);


        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            loadLocation();
        } else {
            showCFDialog(new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
        }


    }

    private void loadLocation() {

        smoothProgressBar.setVisibility(View.VISIBLE);
        messageTv.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                buildGoogleApiClient();
            }
        }, 2500);
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void showCFDialog(final String[] permissions) {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MapsActivity.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET);

        // Title and message
        builder.setTitle("Grant us permission");
        builder.setMessage("In order to function properly, " + getString(R.string.app_name) + " needs permission to access your location.");

        builder.setTextGravity(Gravity.CENTER);

        builder.addButton("Allow Access", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(MapsActivity.this, permissions, 1);
                alertDialog.dismiss();
            }
        });

        builder.setHeaderView(R.layout.dialog_header_permission_layout);
        builder.setCancelable(false);
        alertDialog = builder.show();

    }


    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        if (mLastLocation != null) {
            Geocoder geocoder = new Geocoder(getApplicationContext());

            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses != null && addresses.size() > 0) {

                    Address address = addresses.get(0);
                    String county_name = address.getAdminArea();

                    String country = address.getCountryName();

                    //TODO check if request is from kenya
                    sharedPref.setCountyName(county_name);

                    if (country.equalsIgnoreCase("Kenya")){
                        gotToNextPage();
                    }else {
                        showAlienDialog();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //stop location updates
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadLocation();
            } else {
                finish();
            }
        }
    }


    private void showAlienDialog() {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MapsActivity.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);

        // Title and message
        builder.setTitle("App not available");
        builder.setMessage(getString(R.string.app_name) + " app is only available in Kenya. \n\n Luckily,for the purposes of testing, you are allowed to use the app.");

        builder.setTextGravity(Gravity.CENTER);

        builder.addButton("Continue", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                gotToNextPage();
            }
        });

        builder.setHeaderView(R.layout.dialog_header_permission_layout);
        builder.setCancelable(false);
        alertDialog = builder.show();

    }

   private void gotToNextPage(){
       Intent intent = new Intent(MapsActivity.this, MainActivity.class);
       startActivity(intent);
       finish();

   }

}
