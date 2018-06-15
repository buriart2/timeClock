package timeclock.benji.timeclock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.textView);
    }

    public void clockIn(View view) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        this.text = new EditText(this);
        text.setText(currentDateTimeString);
      //  placeName.setHint("Name of Place");
        layout.addView(text);
        dialog.setView(layout);

        dialog.show();

    }



}
