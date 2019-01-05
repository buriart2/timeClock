package timeclock.benji.timeclock;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.browse.MediaBrowser;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.telephony.PhoneNumberUtils.compare;

public class MainActivity extends AppCompatActivity implements Serializable {
    EditText text, text2;
    EditText jobNum;
    Button cin;
    Button clockinbtn, clockoutbtn;
    String clockintime;
    String clockouttime;
    int outseconds = 0;
    int inseconds = 0;
    Date d1 = null;
    Date d2 = null;
    Employee employee;
    SharedPreferences sharedPref;
    double longitude;
    double latitude;
    String locationProvider = LocationManager.GPS_PROVIDER;
    private LocationManager locationManager;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            System.out.println("LATITUDE: " + location.getLatitude() + " LONGITUDE: " + location.getLongitude());
            new getAddress().execute();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {

        }
    };

    public class getAddress extends AsyncTask<String,Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String response;
                HttpDataHandler http = new HttpDataHandler();
                String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + Double.toString(latitude) + "," + Double.toString(longitude) + "&key=AIzaSyDcP-8PXjLv5ZA34SV3F01t-9CPDcOpJ40";
                response = http.GetHTTPData(url);
                return response;

            } catch (Exception ex) {

            }
            return null;
        }

        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                String address = ((JSONArray)jsonObject.get("results")).getJSONObject(0).get("formatted_address").toString();
                System.out.println("ADDRESS: " + address);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String headerTitle;
        jobNum = (EditText) findViewById(R.id.jobNumber);
        clockoutbtn = (Button) findViewById(R.id.out);
        clockinbtn = (Button) findViewById(R.id.in);
        clockoutbtn.setEnabled(false);

        Intent intent = getIntent();
        employee = (Employee) intent.getSerializableExtra("Employee");
        sharedPref = (SharedPreferences) intent.getSerializableExtra("sharedPref");
       // setTitle(employee.getFname());

        sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String value = sharedPref.getString("isLoggedIn", "");
        System.out.println("VALUE IS " + value);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);

            return;
        }


    }


    public void clockIn(View view) throws ParseException {
        employee.setJobNumber(jobNum.getText().toString());
        System.out.println("JOB NUMBER: " + employee.getJobNumber());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockintime = mdformat.format(calendar.getTime());
        d1 = mdformat.parse(clockintime);
        inseconds = calendar.get(Calendar.SECOND);
        this.text = new EditText(this);
        text.setText(clockintime);

        Toast.makeText(MainActivity.this, "Clock In recorded.",
                Toast.LENGTH_LONG).show();


        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3600000, 0, locationListener);
        }
        System.out.println("clock in done");
        clockoutbtn.setEnabled(true);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3600000, 0, locationListener);
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Permission Needed");
                    alertDialog.setMessage("Without location permissions, this application cannot work as intended.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    System.out.println("DENIED");
            }
                return;
        }
    }


    public void clockOut(View view) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockouttime = mdformat.format(calendar.getTime());
        d2 = mdformat.parse(clockouttime);
        outseconds = calendar.get(Calendar.SECOND);
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        System.out.println("you worked " + diffMinutes + " minutes and " + diffSeconds + " seconds");
        this.text2 = new EditText(this);
        text2.setText(clockouttime);

        Toast.makeText(MainActivity.this, "Clock In recorded.",
                Toast.LENGTH_LONG).show();

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3600000, 0, locationListener);
        }



    }

    @Override
    public void onBackPressed() {
    }




}
