package timeclock.benji.timeclock;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.browse.MediaBrowser;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.telephony.PhoneNumberUtils.compare;

public class MainActivity extends AppCompatActivity implements Serializable  {
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
    double longitude;
    double latitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String headerTitle;
        jobNum = (EditText) findViewById(R.id.jobNumber);
        clockoutbtn = (Button) findViewById(R.id.out);
        clockinbtn = (Button) findViewById(R.id.in);
        clockoutbtn.setEnabled(false);

        //text = (EditText) findViewById(R.id.textView);
        //cin = (Button) findViewById(R.id.button);
        Intent intent = getIntent();
        employee = (Employee) intent.getSerializableExtra("Employee");
        setTitle(employee.getFname());


    }

    public void clockIn(View view) throws ParseException {
        //  String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //AlertDialog.Builder dialog = new AlertDialog.Builder(this);
       // Toast.makeText(this, "Clock in has been recorded",
         //       Toast.LENGTH_LONG).show();
       // clockinbtn.setEnabled(false);
       // clockoutbtn.setEnabled(true);

        employee.setJobNumber(jobNum.getText().toString());
        System.out.println("JOB NUMBER: " + employee.getJobNumber());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockintime = mdformat.format(calendar.getTime());
        d1 = mdformat.parse(clockintime);
        inseconds = calendar.get(Calendar.SECOND);

        //LinearLayout layout = new LinearLayout(this);
        //layout.setOrientation(LinearLayout.VERTICAL);

        this.text = new EditText(this);
        text.setText(clockintime);
        //cin.setEnabled(false);
        //  placeName.setHint("Name of Place");
        //layout.addView(text);
        //dialog.setView(layout);

        //dialog.show();


    }


    public void clockOut(View view) throws ParseException {
       // Toast.makeText(this, "Clock out has been recorded",
         //       Toast.LENGTH_LONG).show();

        // AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockouttime = mdformat.format(calendar.getTime());
        d2 = mdformat.parse(clockouttime);
        outseconds = calendar.get(Calendar.SECOND);
        //Double difference = Double.parseDouble(clockintime) - Double.parseDouble(clockouttime);
        //LinearLayout layout = new LinearLayout(this);
        //layout.setOrientation(LinearLayout.VERTICAL);
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        System.out.println("you worked " + diffMinutes + " minutes and " + diffSeconds + " seconds");
        this.text2 = new EditText(this);
        text2.setText(clockouttime);
        //  placeName.setHint("Name of Place");
        //layout.addView(text2);
        //dialog.setView(layout);

        //dialog.show();
    }


}
