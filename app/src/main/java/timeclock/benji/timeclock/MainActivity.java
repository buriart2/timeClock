package timeclock.benji.timeclock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.telephony.PhoneNumberUtils.compare;

public class MainActivity extends AppCompatActivity {
    EditText text, text2;
    Button cin;
    String clockintime;
    String clockouttime;
    int outseconds = 0;
    int inseconds = 0;
    Date d1 = null;
    Date d2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.textView);
        cin = (Button) findViewById(R.id.button);
        class Employee {
           String clockintime;
           String clockoutime;
           int outseconds;
           int inseconds;
        }

       
    }

    public void clockIn(View view) throws ParseException {
      //  String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockintime = mdformat.format(calendar.getTime());
        d1 = mdformat.parse(clockintime);
        inseconds = calendar.get(Calendar.SECOND);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        this.text = new EditText(this);
        text.setText(clockintime);
        cin.setEnabled(false);
      //  placeName.setHint("Name of Place");
        layout.addView(text);
        dialog.setView(layout);

        dialog.show();

    }


    public void clockOut(View view) throws ParseException {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm:ss");
        clockouttime = mdformat.format(calendar.getTime());
        d2 = mdformat.parse(clockouttime);
        outseconds = calendar.get(Calendar.SECOND);
        //Double difference = Double.parseDouble(clockintime) - Double.parseDouble(clockouttime);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        System.out.println("you worked " + diffMinutes + " minutes and " + diffSeconds + " seconds");
        this.text2 = new EditText(this);
        text2.setText(clockouttime);
        //  placeName.setHint("Name of Place");
        layout.addView(text2);
        dialog.setView(layout);

        dialog.show();
    }
}
