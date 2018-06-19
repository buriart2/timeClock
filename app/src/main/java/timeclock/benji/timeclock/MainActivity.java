package timeclock.benji.timeclock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText text, text2;
    String clockintime;
    String clockouttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.textView);
    }

    public void clockIn(View view) {
      //  String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockintime = mdformat.format(calendar.getTime());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        this.text = new EditText(this);
        text.setText(clockintime);
      //  placeName.setHint("Name of Place");
        layout.addView(text);
        dialog.setView(layout);

        dialog.show();

    }


    public void clockOut(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockouttime = mdformat.format(calendar.getTime());
        //Double difference = Double.parseDouble(clockintime) - Double.parseDouble(clockouttime);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        //System.out.println("clock in as double is: " + Double.parseDouble(clockintime));
        this.text2 = new EditText(this);
        text2.setText(clockouttime);
        //  placeName.setHint("Name of Place");
        layout.addView(text2);
        dialog.setView(layout);

        dialog.show();
    }
}
