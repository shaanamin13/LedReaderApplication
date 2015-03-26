package com.example.shaan.ledreaderapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class logActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);


        Button emailButton = (Button) findViewById(R.id.email_btn);

        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doSendFile();

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void doSendFile() {
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/led/ledLogs.txt";
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { "send@uiowa.edu" });
        i.putExtra(Intent.EXTRA_SUBJECT, "Circuit Breaker Status Log");
        i.putExtra(Intent.EXTRA_TEXT, "Attached is the text file for the circuit breaker status log. \n Sent via Android Smartphone.");

        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(fileName));

        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getBaseContext(),
                    "There are no email clients installed.", Toast.LENGTH_SHORT)
                    .show();
        }

    }


}
