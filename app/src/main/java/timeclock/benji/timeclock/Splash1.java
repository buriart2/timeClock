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
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.text.ParseException;

import static android.app.PendingIntent.getActivity;

public class Splash1 extends AppCompatActivity implements Serializable {

    EditText input;
    Button submit;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);

        input = (EditText) findViewById(R.id.full_name);

        submit = (Button) findViewById(R.id.submit);

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String isLoggedIn = sharedPref.getString("isLoggedIn", "");
        if(!isLoggedIn.equals("true")) {
            System.out.println("NO LOGIN YET");
        }
        else {
            System.out.println("LOGIN CONFIRMED");
            Intent main = new Intent(this, MainActivity.class);
            startActivityForResult(main, 1);
        }

    }

    public void submit(View view) throws ParseException {
        Employee employee = new Employee(input.getText().toString(), "Uriarte");

//        SharedPreferences sharedPref = Splash1.this.getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean(isLoggedIn, true);
//        editor.commit();
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("isLoggedIn", "true");
        editor.apply();


        Intent mainScreen = new Intent(this, MainActivity.class);
        mainScreen.putExtra("Employee", (Serializable) employee);
        //mainScreen.putExtra("sharedpref", (Serializable) sharedPref);
        startActivityForResult(mainScreen, 1);

    }

}
