package timeclock.benji.timeclock;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


    }

    public void submit(View view) throws ParseException {
        Employee employee = new Employee(input.getText().toString(), "Uriarte");

        Intent mainScreen = new Intent(this, MainActivity.class);
        mainScreen.putExtra("Employee", (Serializable) employee);
        startActivityForResult(mainScreen, 1);
        //System.out.println(input.getText().toString());
    }






}
